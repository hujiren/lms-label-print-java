package com.apl.lms.label.print.service.impl;

import com.apl.lib.constants.CommonStatusCode;
import com.apl.lib.exception.AplException;
import com.apl.lms.label.print.utils.ConvertImageUtil;
import org.dom4j.Element;
import java.io.File;
import java.io.IOException;

/**
 * @author hjr start
 * @Classname DHLLabelPrintServiceImpl
 * @Date 2021/1/6 21:31
 */
public class DHLLabelPrintServiceImpl {

    /**
     * 创建dhl label pdf
     * @param xmlFile
     * @return
     */
    public static String buildLabel(File xmlFile) {

        String labelFileFullPath = extractDHLLabelBase64Code(xmlFile);

        return labelFileFullPath;
    }

    /**
     * 构建dhl invoice pdf
     * @param xmlFile
     * @return
     */
    public static String buildInvoice(File xmlFile) {

        String invoiceFileFullPath = extractDHLInvoiceBase64Code(xmlFile);

        return invoiceFileFullPath;
    }

    /**
     * 从xml中提取label  base64编码
     * @param xmlFile
     */
    public static String extractDHLLabelBase64Code(File xmlFile){

        String saveFileFullPath;

        try {

            Element rootNode = ConvertImageUtil.createXmlElement(xmlFile);
            String xmlFileAbsolutePath = xmlFile.getAbsolutePath();
            //寻找base64对应的标签
            String base64Data = rootNode.element("LabelImage").element("OutputImage").getText();

            //组装文件全路径
            saveFileFullPath = xmlFileAbsolutePath.replaceAll(".xml", "-label.pdf");
            //base64转图片并写出到指定路径
            boolean isSuccess = ConvertImageUtil.base64GeneratorImage(base64Data, saveFileFullPath);
            if(!isSuccess)
                throw new AplException(CommonStatusCode.SYSTEM_FAIL);

        } catch (IOException e) {

           throw new AplException(e.getMessage(), e.getCause().toString());
        }
        //成功
        return saveFileFullPath;
    }

    public static String extractDHLInvoiceBase64Code(File xmlFile){

        String saveFileFullPath;

        try {
            Element rootNode = ConvertImageUtil.createXmlElement(xmlFile);
            String xmlFileAbsolutePath = xmlFile.getAbsolutePath();
            //寻找base64对应的标签
            String base64Data = rootNode.element("LabelImage").element("MultiLabels").element("MultiLabel").element("DocImageVal").getText();

            //组装文件全路径
            saveFileFullPath = xmlFileAbsolutePath.replaceAll(".xml", "-voice.pdf");
            //base64转图片并写出到指定路径
            boolean isSuccess = ConvertImageUtil.base64GeneratorImage(base64Data, saveFileFullPath);
            if(!isSuccess)
                throw new AplException(CommonStatusCode.SYSTEM_FAIL);

        } catch (IOException e) {
            throw new AplException(e.getMessage(), e.getCause().toString());
        }

        //成功
        return saveFileFullPath;
    }


}
