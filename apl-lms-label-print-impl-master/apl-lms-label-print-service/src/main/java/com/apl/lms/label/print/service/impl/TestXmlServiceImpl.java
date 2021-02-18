package com.apl.lms.label.print.service.impl;

import com.apl.lib.utils.XmlUtil;
import com.apl.lms.label.print.bo.DHLCommonBaseLabelBo2;
import com.apl.lms.label.print.po.DHLPiecePo;
import com.apl.lms.label.print.utils.Context;
import com.apl.lms.label.print.utils.JasperHelper;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjr start
 * @Classname TestXmlServiceImpl
 * @Date 2021/1/8 19:30
 */
@Service
public class TestXmlServiceImpl {

    @Autowired
    JasperHelper jasperHelper;

    public static void main(String[] args) throws Exception {
        Element configElement = loadXmlFile("G:\\java\\apl-lms-label-print-java\\config\\label\\TestdhlPieceConfig.xml");
        Element dataRootElement = loadXmlFile("G:\\java\\apl-lms-label-print-java\\config\\label\\dhlResponse.xml");

        //List<Attribute> dataPath = getDataPath(configElement.element("Pieces"));
       // List<Element> targetNodeList = getTargetXmlValue(dataPath, targetElement);
//        TestPo2 po = fill(targetNodeList, new TestPo2());
        //DHLPo po = new DHLPo();
       // XmlUtil.fillEntity(configElement, targetElement, po);

        List<DHLPiecePo> list = XmlUtil.fillList(dataRootElement.element("Pieces"), configElement.element("pieces"), "com.apl.lms.label.print.po.DHLPiecePo");

        System.out.println(list);

    }

    /**
     * 加载xml文件
     * @param xmlPath
     * @return
     * @throws DocumentException
     */
    public static Element loadXmlFile(String xmlPath) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        File file = new File(xmlPath);
        if (!file.exists())
            return null;
        Document xmlDocument = saxReader.read(file);
        Element rootNode = xmlDocument.getRootElement();
        return rootNode;

    }

    public static List<Attribute> getDataPath(Element configElement){

        List<Attribute> pathList = new ArrayList<>();

        List<Element> elements = configElement.elements();
        for (Element element : elements) {
            Attribute path = element.attribute("path");
            pathList.add(path);
        }
        if(pathList.size() < 1)
            return null;

        return pathList;

    }

    public static List<Element> getTargetXmlValue(List<Attribute> attributes, Element targetElement){

        List<Element> targetNodeList = new ArrayList<>();

        for (Attribute attribute : attributes) {
            String dataPath = attribute.getStringValue();
            String[] arr = dataPath.split("/");
            Element targetNode = targetElement;
            for (String s : arr) {
                targetNode = targetNode.element(s);
            }
            targetNodeList.add(targetNode);
        }

        return targetNodeList;
    }

    public static <T> T fill(List<Element> targetNodeList, T po) throws IllegalAccessException {
        Class<?> clazz = po.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Element element : targetNodeList) {
            for (Field field : declaredFields) {
                if(element.getQualifiedName().equals(field.getName())){
                    field.setAccessible(true);
                    field.set(po, element.getText());
                }
            }
        }
        return po;
    }

    public void printDHLLabel2(String configXmlPath, String targetXmlPath) {
        //com.apl.lib.utils.XmlUtil
        try {
            XmlUtil xmlUtil = new XmlUtil();

            Element configRootNode = xmlUtil.loadXmlFile(configXmlPath);
            Element targetRootNode = xmlUtil.loadXmlFile(targetXmlPath);
            Element eShipper = targetRootNode.element("Shipper");
            //Element e2= targetRootNode.element("Shipper").element("AddressLine");
            String addressLine1 = null, addressLine2 = null, addressLine3 = null;

            List<Element> es = eShipper.elements();
            for (int i = 0; i < es.size(); i++) {
                //System.out.println(es.get(i).getName()+":"+ es.get(i).getText());
                if (es.get(i).getName().equals("AddressLine")) {
                    if (null == addressLine1)
                        addressLine1 = es.get(i).getText();
                    else if (null == addressLine2)
                        addressLine2 = es.get(i).getText();
                    else if (null == addressLine3)
                        addressLine3 = es.get(i).getText();
                } else if (null != addressLine1) {
                    break;
                }
            }

            DHLCommonBaseLabelBo2 dhlCommonBaseLabelBo2 = new DHLCommonBaseLabelBo2();

            XmlUtil.fillEntity(configRootNode, targetRootNode, dhlCommonBaseLabelBo2);
            dhlCommonBaseLabelBo2.setShipperAddressLine1(addressLine1);
            dhlCommonBaseLabelBo2.setShipperAddressLine2(addressLine2);
            dhlCommonBaseLabelBo2.setShipperAddressLine3(addressLine3);

            List<DHLCommonBaseLabelBo2> dataSources = new ArrayList<>();
            dataSources.add(dhlCommonBaseLabelBo2);
            Map<String, Object> params = new HashMap<>();

            jasperHelper.exportPdf(
                    "apl-lms-label-print-java/report/template",
                    "template.jasper",
                    "DHLLabel.pdf",
                    params,
                    dataSources,
                    Context.getRequest(),
                    Context.httpServletResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
