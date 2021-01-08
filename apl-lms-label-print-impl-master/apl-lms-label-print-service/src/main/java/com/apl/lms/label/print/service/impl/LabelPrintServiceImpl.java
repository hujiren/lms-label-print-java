package com.apl.lms.label.print.service.impl;

import com.apl.lib.exception.AplException;
import com.apl.lms.label.print.dao.LabelPrintDao;
import com.apl.lms.label.print.bo.ShipOrderBo;
import com.apl.lms.label.print.service.LabelPrintService;
import com.apl.lms.label.print.utils.JasperHelper;
import com.apl.lms.label.print.vo.InvoiceBase64Vo;
import io.swagger.models.auth.In;
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
        THE_XML_IS_NOT_RETRIEVED_FROM_THE_DOCKING_PLATFORM("PLEASE_CONNECT_TO_THE_PLATFORM_FIRST_AND_GET_THE_XML_FILE", "尚未对接平台获取xml");

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

                } else if (shipOrderBo.getChannelCategory().toLowerCase().contains("ups")) {
                    if (docType.equals(1))
                        pdfFileFullPath = UPSLabelPrintServiceImpl.buildLabel(xmlFile);
                    else
                        pdfFileFullPath = UPSLabelPrintServiceImpl.buildInvoice(xmlFile);
                }else{
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
        Boolean isExists = existsFile(labelFileFullPath);

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
            Boolean isExists2 = existsFile(labelFileFullPath);

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


        //如果对应时间的label不存在, 找xml
        String xmlFileFullPath = labelPath + formatOrderTime + "/" + trackingSn + ".xml";
        if (!existsFile(xmlFileFullPath))
            xmlFileFullPath = labelPath + shipOrderBo.getReceivingTime() + "/" + trackingSn + ".xml";

        if (!existsFile(xmlFileFullPath))
            throw new AplException(LabelPrintServiceImplEnum.THE_XML_IS_NOT_RETRIEVED_FROM_THE_DOCKING_PLATFORM.code,
                    LabelPrintServiceImplEnum.THE_XML_IS_NOT_RETRIEVED_FROM_THE_DOCKING_PLATFORM.msg);

        return xmlFileFullPath;
    }


    /**
     * 判断pdf文件是否存在
     *
     * @param filePath
     * @return
     */
    public Boolean existsFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return false;
        return true;
    }


    /**
     * 下载label文件
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
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileFullPath);

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
     * 返回发票base64编码
     * @param id
     * @return
     */
    @Override
    public InvoiceBase64Vo getInvoiceBase64(Long id) {
        InvoiceBase64Vo invoiceBase64Vo = new InvoiceBase64Vo();
        invoiceBase64Vo.setBase64Code("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");
        invoiceBase64Vo.setTrackingSn("5689078080");
        return null;
    }
}
