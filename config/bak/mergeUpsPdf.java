package com.apl.lms.label.print.utils;

import com.itextpdf.text.pdf.PdfWriter;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

class mergeUpsPdf{

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("G:\\waybill\\label\\2020-12-17\\1Z75863V6600005216.xml");
        String savePath = "G:\\waybill\\label\\2020-12-17";
        mergeUpsPdf(xmlFile, savePath);
    }

    static void mergeUpsPdf(File xmlFile, String savePath) throws Exception {

        if (savePath == null) {
            return;
        }
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);
        Element rootNode = document.getRootElement();
        Element elShipment = rootNode.element("Body").element("ShipmentResponse")
                .element("ShipmentResults");

        String trackNo = elShipment.elementText("ShipmentIdentificationNumber");

        File outDir = new File(savePath);
        if(!outDir.exists())
            outDir.mkdirs();
        String saveFile = savePath + "\\" + trackNo + "-label.pdf";
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

    }
}
