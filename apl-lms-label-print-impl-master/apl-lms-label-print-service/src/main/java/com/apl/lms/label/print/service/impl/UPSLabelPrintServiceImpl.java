package com.apl.lms.label.print.service.impl;

import com.apl.lib.exception.AplException;
import com.apl.lms.label.print.bo.ShipOrderBo;
import com.apl.lms.label.print.bo.UPSInvoiceBo;
import com.apl.lms.label.print.bo.WaybillCommodityBo;
import com.apl.lms.label.print.dao.LabelPrintDao;
import com.apl.lms.label.print.mapper.UPSLabelPrintMapper;
import com.apl.lms.label.print.utils.Context;
import com.apl.lms.label.print.utils.ConvertImageUtil;
import com.apl.lms.label.print.utils.ImgUtil;
import com.apl.lms.label.print.utils.JasperHelper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjr start
 * @Classname UPSLabelPrintServiceImpl
 * @Date 2021/1/6 21:34
 */
@Service
public class UPSLabelPrintServiceImpl {

    @Autowired
    LabelPrintDao labelPrintDao;

    @Autowired
    JasperHelper jasperHelper;

    @Autowired
    UPSLabelPrintMapper upsLabelPrintMapper;

    public static String buildLabel(File xmlFile) {

        String saveFileFullPath = extractUPSBase64Code(xmlFile, 1);
        return saveFileFullPath;
    }

    public static String buildInvoice(File xmlFile){

        String saveFileFullPath = extractUPSBase64Code(xmlFile, 2);
        return saveFileFullPath;
    }

    public void previewUPSInvoice(Long id){

        UPSInvoiceBo upsInvoiceBo = upsLabelPrintMapper.getWaybillContacts(id);
        if(null == upsInvoiceBo)
            upsInvoiceBo = labelPrintDao.getWaybillContacts(id,2);
        if(null == upsInvoiceBo)
            return;
        List<WaybillCommodityBo> waybillCommodityList = upsLabelPrintMapper.getWaybillCommodityList(id);

        upsInvoiceBo.setWaybillCommodity(waybillCommodityList);

        List<UPSInvoiceBo> upsInvoiceBoList = new ArrayList<>();
        upsInvoiceBoList.add(upsInvoiceBo);
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("SUBREPORT_DIR", "G:/Jaspersoft_workspace/MyReports/Print/");
        jasperHelper.exportPdf(
                "apl-lms-label-print-java/report/template",
                "upsInvoice.jasper",
                "upsInvoice.pdf",
                parameterMap,
                upsInvoiceBoList,
                Context.getRequest(),
                Context.httpServletResponse());
    }

    public static String getUpsInvoice(String labelPath, ShipOrderBo shipOrderBo){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String orderTimeStr = df.format(shipOrderBo.getOrderTime());
        String receivingTimeStr = df.format(shipOrderBo.getReceivingTime());
        String filePath = labelPath + orderTimeStr + "/" + shipOrderBo.getTrackingSn() + "-invoice.pdf";
        Boolean existsFile = ConvertImageUtil.existsFile(filePath);

        if(!existsFile){
            filePath = labelPath + receivingTimeStr + "/" + shipOrderBo.getTrackingSn() + "-invoice.pdf";
            existsFile = ConvertImageUtil.existsFile(filePath);
            if(!existsFile)
                throw new AplException(LabelPrintServiceImpl.LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.toString(),
                        LabelPrintServiceImpl.LabelPrintServiceImplEnum.NO_CORRESPONDING_DATA.toString());
        }

        return filePath;
    }

    static String extractUPSBase64Code(File xmlFile, Integer docType){

        String saveFileFullPath;
        try {
            Element rootNode = ConvertImageUtil.createXmlElement(xmlFile);

            Element elShipment = rootNode.element("Body").element("ShipmentResponse")
                    .element("ShipmentResults");

            String trackNo = elShipment.elementText("ShipmentIdentificationNumber");

            String xmlAbsolutePath = xmlFile.getAbsolutePath();

            int index = 0;
            if(xmlAbsolutePath.contains("/"))
                index = xmlAbsolutePath.lastIndexOf("/");
            else if(xmlAbsolutePath.contains("\\")){
                index = xmlAbsolutePath.lastIndexOf("\\");
            }

            String savePath = xmlAbsolutePath.substring(0, index);

            File outDir = new File(savePath);
            if(!outDir.exists())
                outDir.mkdirs();

            saveFileFullPath = xmlAbsolutePath.replaceAll(".xml", "-label.pdf");

            saveFileFullPath = savePath + "\\" + trackNo + "-label.pdf";
            FileOutputStream fos = new FileOutputStream(saveFileFullPath);
            Document doc = new Document(null, 0, 0, 0, 0);
            PdfWriter.getInstance(doc, fos);

            java.awt.image.BufferedImage image = null;
            Element node = null;
            String base64Data = null;
            List<Element> lisNodes = elShipment.elements();
            int count = lisNodes.size();

            for (int i = 3; i < count; i++) {
                node = lisNodes.get(i);
                if (node.getName().equals("PackageResults")) {

                    base64Data = node.element("ShippingLabel").elementText("GraphicImage");
                    image = ImgUtil.GetBufferedImage(base64Data);
                    image = ImgUtil.rotateCounterclockwise90(image);
                    image = ImgUtil.rotateHorizon(image);
                    ImgUtil.addPdfImage(doc, image, "png");
                }
            }

            doc.close();
            fos.close();
            doc = null;
            fos = null;

        } catch (Exception e) {
            throw new AplException(e.getMessage(), e.getCause().toString());
        }

        return saveFileFullPath;
    }

}
