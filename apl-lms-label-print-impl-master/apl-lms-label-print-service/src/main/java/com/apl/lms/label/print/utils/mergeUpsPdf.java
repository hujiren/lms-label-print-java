package com.apl.lms.label.print.utils;

import com.itextpdf.text.pdf.PdfWriter;
import org.dom4j.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

class mergeUpsPdf{

    public static void main(String[] args) {
        File xmlFile = new File("G:\\waybill\\label\\2020-12-17\\1Z75863V6600005216.xml");
    }

    void mergeUpsPdf(File xmlFile, String savePath, Element rootNode) throws Exception {

        if (savePath == null) {
            return;
        }

//        String strTime = DateHelper.dateToStr(new Date(), "yy-MM-dd HH:mm:ss");
//        System.err.println("--开始转换UPS图片base64代码转换为PDF--" + strTime);

        Element elShipment = rootNode.element("Body").element("ShipmentResponse")
                .element("ShipmentResults");

        String trackNo = elShipment.elementText("ShipmentIdentificationNumber");

        File outDir = new File(savePath);
        outDir.mkdirs();
        String saveFile = savePath + "\\" + trackNo + ".pdf";
        FileOutputStream fos = new FileOutputStream(saveFile);
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document(null, 0, 0, 0, 0);
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

//        strTime = DateHelper.dateToStr(new Date(), "yy-MM-dd HH:mm:ss");
//        System.err.println("--结束转换UPS图片base64代码转换为PDF--" + strTime);
    }
}
