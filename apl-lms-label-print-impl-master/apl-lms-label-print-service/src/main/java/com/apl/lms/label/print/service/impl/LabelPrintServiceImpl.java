package com.apl.lms.label.print.service.impl;

import com.apl.lib.exception.AplException;
import com.apl.lms.label.print.dao.LabelPrintDao;
import com.apl.lms.label.print.bo.ShipOrderBo;
import com.apl.lms.label.print.service.LabelPrintService;
import com.apl.lms.label.print.utils.ConvertImageUtil;
import com.apl.lms.label.print.utils.ImgUtil;
import com.apl.lms.label.print.utils.JasperHelper;
import com.apl.lms.label.print.vo.InvoiceBase64Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author hjr start
 * @Classname TestPrintServiceImpl
 * @Date 2020/12/28 15:11
 */
@Service
public class LabelPrintServiceImpl implements LabelPrintService {

    enum LabelPrintServiceImplEnum {

        NO_CORRESPONDING_DATA("NO_CORRESPONDING_DATA", "没有对应数据"),
        THE_XML_IS_NOT_RETRIEVED_FROM_THE_DOCKING_PLATFORM("PLEASE_CONNECT_TO_THE_PLATFORM_FIRST_AND_GET_THE_XML_FILE", "尚未对接平台获取xml"),
        THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL("THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL", "该渠道没有发票");

        private String code;
        private String msg;

        LabelPrintServiceImplEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    static final Logger logger = LoggerFactory.getLogger(LabelPrintServiceImpl.class);

    JasperHelper jasperHelper;

    @Autowired
    LabelPrintDao labelPrintDao;

    @Autowired
    UPSLabelPrintServiceImpl upsLabelPrintServiceImpl;

    @Value("${lms.print.label-path}")
    String labelPath;

    public LabelPrintServiceImpl(JasperHelper jasperHelper) {

        this.jasperHelper = jasperHelper;
    }


    /**
     * @param response
     * @param id
     */
    @Override
    public void printLabel(HttpServletResponse response, Long id, Integer docType) {

        ShipOrderBo shipOrderBo = getShipOrderBo(id);

        //获取下单时间和转单号
        if (null == shipOrderBo || null == shipOrderBo.getTrackingSn() || null == shipOrderBo.getOrderTime())
            throw new AplException(LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.code, LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.msg);

        Integer orderOrWaybillType = shipOrderBo.getOrderOrWaybillType();
        Date receivingTime = labelPrintDao.getWaybillReceivingTimeByWbId(shipOrderBo.getWaybillId());
        shipOrderBo.setReceivingTime(receivingTime);

        String pdfFileFullPath = getPdfPath(shipOrderBo, orderOrWaybillType, docType);

        if (null == pdfFileFullPath) {
            String xmlFileFullPath = getXmlFilePath(shipOrderBo);
            if (null != xmlFileFullPath) {
                File xmlFile = new File(xmlFileFullPath);
                //如果xml不为空 则判断渠道类型和文档类型, 提取对应的xml数据生成新的pdf;
                if (shipOrderBo.getChannelCategory().toLowerCase().contains("dhl")) {
                    if (docType.equals(1))
                        pdfFileFullPath = DHLLabelPrintServiceImpl.buildLabel(xmlFile);
                    else
                        pdfFileFullPath = DHLLabelPrintServiceImpl.buildInvoice(xmlFile);

                } else if(shipOrderBo.getChannelCategory().toLowerCase().contains("ups")) {
                    if (docType.equals(1))
                        pdfFileFullPath = UPSLabelPrintServiceImpl.buildLabel(xmlFile);
                    else
                        throw new AplException(LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.code,
                                LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.msg);

                }else if(shipOrderBo.getChannelCategory().toLowerCase().contains("fedex")){
                    if (docType.equals(1))
                        pdfFileFullPath = FEDEXLabelPrintServiceImpl.buildLabel(xmlFile);
                    else
                        pdfFileFullPath = FEDEXLabelPrintServiceImpl.buildInvoice(xmlFile);
                }
                else if(shipOrderBo.getChannelCategory().toLowerCase().contains("aramex")){
                    if (docType.equals(1))
                        pdfFileFullPath = AramexLabelPrintServiceImpl.buildLabel(xmlFile);
                    else
                        throw new AplException(LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.code,
                                LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.msg);
                }
                else{
                    throw new AplException(LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.code, LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.msg);
                }
            }
        }

        //下载
        writePdfFile(response, pdfFileFullPath);
        //回写
        if (docType.equals(1) && orderOrWaybillType.equals(1))
            labelPrintDao.updateIsCreateLabelById(id, 1, 1);
        else
            labelPrintDao.updateIsCreateLabelById(id, 1, 2);
    }

    /**
     * 获取ShipOrderBo
     *
     * @param id
     * @return
     */
    public ShipOrderBo getShipOrderBo(Long id) {

        ShipOrderBo shipOrderBo;

        //根据订单id获取下单时间和转单号 select by id
        shipOrderBo = labelPrintDao.getShipOrder(id, 1);

        //根据运单id获取下单时间和转单号
        if (null == shipOrderBo) {
            //select by waybill_id
            shipOrderBo = labelPrintDao.getShipOrder(id, 2);
            if (null != shipOrderBo)
                shipOrderBo.setOrderOrWaybillType(2);
        }
        return shipOrderBo;
    }

    /**
     * 获取pdf文件路径
     *
     * @param shipOrderBo
     * @param orderOrWaybillType
     * @param docType
     * @return
     */
    public String getPdfPath(ShipOrderBo shipOrderBo, Integer orderOrWaybillType, Integer docType) {

        //转单号
        String trackingSn = shipOrderBo.getTrackingSn();
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatOrderTime = sdf.format(shipOrderBo.getOrderTime());

        //组装文件全路径  G:/waybill/label/2020-12-31/123456789-label.pdf
        String labelFileFullPath = null;

        if (docType.equals(1))
            labelFileFullPath = labelPath + formatOrderTime + "/" + trackingSn + "-label.pdf";
        else
            labelFileFullPath = labelPath + formatOrderTime + "/" + trackingSn + "-invoice.pdf";

        //检查文件是否存在
        Boolean isExists = ConvertImageUtil.existsFile(labelFileFullPath);

        if (isExists) {
            //如果存在,直接下载
            return labelFileFullPath;
        }

        String formatReceivingTime = null;
        //说明是运单id, 需要再查一次收货时间
        if (orderOrWaybillType.equals(2)) {
            Date receivingTime = shipOrderBo.getReceivingTime();
            formatReceivingTime = sdf.format(receivingTime);
            if (docType.equals(1))
                labelFileFullPath = labelPath + formatReceivingTime + "/" + trackingSn + "-label.pdf";
            else
                labelFileFullPath = labelPath + formatReceivingTime + "/" + trackingSn + "-invoice.pdf";

            //检查文件是否存在
            Boolean isExists2 = ConvertImageUtil.existsFile(labelFileFullPath);

            if (isExists2) {
                //如果存在,直接下载
                return labelFileFullPath;
            }
        }

        return null;

    }

    /**
     * 获取xmlPath
     *
     * @param shipOrderBo
     * @return
     */
    public String getXmlFilePath(ShipOrderBo shipOrderBo) {

        //转单号
        String trackingSn = shipOrderBo.getTrackingSn();
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatOrderTime = sdf.format(shipOrderBo.getOrderTime());
        String formatReceivingTime = sdf.format(shipOrderBo.getReceivingTime());

        //如果对应时间的label不存在, 找xml  fedex有两个xml文件, label和invoice不在同一个xml中
        String xmlFileFullPath = labelPath + formatOrderTime + "/" + trackingSn + ".xml";
        if(shipOrderBo.getChannelCategory().toLowerCase().contains("fedex"))
            xmlFileFullPath = labelPath + formatOrderTime + "/" + trackingSn + "-invoice.xml";

        if (!ConvertImageUtil.existsFile(xmlFileFullPath)){
            xmlFileFullPath = labelPath + formatReceivingTime + "/" + trackingSn + ".xml";
            if(shipOrderBo.getChannelCategory().toLowerCase().contains("fedex"))
                xmlFileFullPath = labelPath + formatReceivingTime + "/" + trackingSn + "-invoice.xml";
        }

        if (!ConvertImageUtil.existsFile(xmlFileFullPath))
            throw new AplException(LabelPrintServiceImplEnum.THE_XML_IS_NOT_RETRIEVED_FROM_THE_DOCKING_PLATFORM.code,
                    LabelPrintServiceImplEnum.THE_XML_IS_NOT_RETRIEVED_FROM_THE_DOCKING_PLATFORM.msg);

        return xmlFileFullPath;
    }

    /**
     * 下载pdf文件
     *
     * @param response
     * @param fileFullPath
     */
    public void writePdfFile(HttpServletResponse response, String fileFullPath) {

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            // 这里URLEncoder.encode可以防止中文乱码
//            fileFullPath = URLEncoder.encode(fileFullPath, "UTF-8").replaceAll("\\+", "%20");
            //response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileFullPath);取消注释这一行则变成在线预览

            OutputStream os = response.getOutputStream();
            InputStream is = null;
            File sourceFile = null;

            sourceFile = new File(fileFullPath);
            is = new FileInputStream(fileFullPath);

            byte[] sourceFileByteArr = new byte[(int) sourceFile.length()];
            int i = -1;
            i = is.read(sourceFileByteArr);
            if (i != -1)
                os.write(sourceFileByteArr);

            if (null != os) {
                os.flush();
                os.close();
            }
            if (null != is)
                is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 返回发票PDF的base64编码
     * @param id
     * @return
     */
    @Override
    public InvoiceBase64Vo getInvoiceBase64(Long id) {

        //DHL不需要返回发票
        // 根据id找到转单号, 和日期
        ShipOrderBo shipOrderBo = getShipOrderBo(id);

        if(null == shipOrderBo || shipOrderBo.getTrackingSn() == null || shipOrderBo.getChannelCategory() == null)
            throw new AplException(LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.code, LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.msg);

        Date receivingTime = labelPrintDao.getWaybillReceivingTimeByWbId(shipOrderBo.getWaybillId());
        shipOrderBo.setReceivingTime(receivingTime);

        String filePath = null;

        if(shipOrderBo.getChannelCategory().toLowerCase().contains("dhl"))
            throw new AplException(LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.code,
                    LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.msg);

        else if(shipOrderBo.getChannelCategory().toLowerCase().contains("ups")){//有现成的pdf(暂用DHL的)暂时先直接返回path
            filePath = UPSLabelPrintServiceImpl.getUpsInvoice(labelPath, shipOrderBo);

        }else if(shipOrderBo.getChannelCategory().toLowerCase().contains("fedex")) {//要先通过xml生成pdf, 再返回path
            filePath = FEDEXLabelPrintServiceImpl.getFedexInvoice(labelPath, shipOrderBo);

        }else if(shipOrderBo.getChannelCategory().toLowerCase().contains("aramex"))
            throw new AplException(LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.code,
                    LabelPrintServiceImplEnum.THERE_IS_NO_INVOICE_FOR_THIS_CHANNEL.msg);

        //找到数据库中对应的数据

        //填充到jasper模板,并输出到日期和转单号拼接好的路径
//        jasperHelper.exportPdf("","","", null, null, null, null);

        String pdfBaseCode = ImgUtil.PdfToBase64(filePath);
        InvoiceBase64Vo invoiceBase64Vo = new InvoiceBase64Vo();
        invoiceBase64Vo.setBase64Code(pdfBaseCode);
        invoiceBase64Vo.setTrackingSn(shipOrderBo.getTrackingSn());
        return invoiceBase64Vo;
    }


    @Override
    public void reviewInvoice(Long id) {
        upsLabelPrintServiceImpl.previewUPSInvoice(id);
    }
}
