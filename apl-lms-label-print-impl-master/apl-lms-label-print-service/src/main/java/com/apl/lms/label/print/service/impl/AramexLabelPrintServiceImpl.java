package com.apl.lms.label.print.service.impl;

import com.apl.lib.constants.CommonStatusCode;
import com.apl.lib.exception.AplException;
import com.apl.lms.label.print.utils.ConvertImageUtil;
import org.dom4j.Element;
import java.io.File;
import java.io.IOException;

/**
 * @author hjr start
 * @Classname AramexLabelPrintServiceImpl
 * @Date 2021/1/7 17:54
 */
public class AramexLabelPrintServiceImpl {

    public static void main(String[] args) throws IOException {
        String base64Code = "PCFET0NUWVBFIEhUTUwgUFVCTElDICItLy9JRVRGLy9EVEQgSFRNTCAzLjIvL0VOIj4KPGh0bWw+PGhlYWQ+PHRpdGxlPgpWaWV3L1ByaW50IExhYmVsPC90aXRsZT48bWV0YSBjaGFyc2V0PSJVVEYtOCI+PC9oZWFkPjxzdHlsZT4KICAgIC5zbWFsbF90ZXh0IHtmb250LXNpemU6IDgwJTt9CiAgICAubGFyZ2VfdGV4dCB7Zm9udC1zaXplOiAxMTUlO30KPC9zdHlsZT4KPGJvZHkgYmdjb2xvcj0iI0ZGRkZGRiI+CjxkaXYgY2xhc3M9Imluc3RydWN0aW9ucy1kaXYiPgo8dGFibGUgY2xhc3M9Imluc3RydWN0aW9ucy10YWJsZSIgbmFtZWJvcmRlcj0iMCIgY2VsbHBhZGRpbmc9IjAiIGNlbGxzcGFjaW5nPSIwIiB3aWR0aD0iNjAwIj48dHI+Cjx0ZCBoZWlnaHQ9IjM1MCIgYWxpZ249ImxlZnQiIHZhbGlnbj0idG9wIj4KPEIgY2xhc3M9ImxhcmdlX3RleHQiPlZpZXcvUHJpbnQgTGFiZWw8L0I+CiZuYnNwOzxicj4KJm5ic3A7PGJyPgo8b2wgY2xhc3M9InNtYWxsX3RleHQiPiA8bGk+PGI+UHJpbnQgdGhlIGxhYmVsOjwvYj4gJm5ic3A7ClNlbGVjdCBQcmludCBmcm9tIHRoZSBGaWxlIG1lbnUgaW4gdGhpcyBicm93c2VyIHdpbmRvdyB0byBwcmludCB0aGUgbGFiZWwgYmVsb3cuPGJyPjxicj48bGk+PGI+CkN1c3RvbXMgSW52b2ljZSA8L2I+ICZuYnNwOwotIDMgY29waWVzIG9mIGEgY29tcGxldGVkIGN1c3RvbXMgaW52b2ljZSBhcmUgcmVxdWlyZWQgZm9yIHNoaXBtZW50cyB3aXRoIGEgY29tbWVyY2lhbCB2YWx1ZSBiZWluZyBzaGlwcGVkIHRvL2Zyb20gbm9uLUVVIGNvdW50cmllcy4gIFBsZWFzZSBpbnN1cmUgdGhlIGN1c3RvbXMgaW52b2ljZSBjb250YWlucyBhZGRyZXNzIGluZm9ybWF0aW9uLCBwcm9kdWN0IGRldGFpbCAtIGluY2x1ZGluZyB2YWx1ZSwgc2hpcG1lbnQgZGF0ZSBhbmQgeW91ciBzaWduYXR1cmUuPGJyPjxicj48bGk+PGI+CkZvbGQgdGhlIHByaW50ZWQgbGFiZWwgYXQgdGhlIGRvdHRlZCBsaW5lLjwvYj4gJm5ic3A7ClBsYWNlIHRoZSBsYWJlbCBpbiBhIFVQUyBTaGlwcGluZyBQb3VjaC4gSWYgeW91IGRvIG5vdCBoYXZlIGEgcG91Y2gsIGFmZml4IHRoZSBmb2xkZWQgbGFiZWwgdXNpbmcgY2xlYXIgcGxhc3RpYyBzaGlwcGluZyB0YXBlIG92ZXIgdGhlIGVudGlyZSBsYWJlbC48YnI+PGJyPjxsaT48Yj5QaWNrdXAgYW5kIERyb3Atb2ZmPC9iPjx1bD48bGk+RGFpbHkgUGlja3VwIGN1c3RvbWVyczogSGF2ZSB5b3VyIHNoaXBtZW50KHMpIHJlYWR5IGZvciB0aGUgZHJpdmVyIGFzIHVzdWFsLiAgIDxsaT5UbyBTY2hlZHVsZSBhIFBpY2t1cCBvciB0byBmaW5kIGEgZHJvcC1vZmYgbG9jYXRpb24sIHNlbGVjdCB0aGUgUGlja3VwIG9yIERyb3Atb2ZmIGljb24gZnJvbSB0aGUgdG9vbCBiYXIuIDwvdWw+PGJyPjxsaT5UbyBhY2tub3dsZWRnZSB5b3VyIGFjY2VwdGFuY2Ugb2YgdGhlIG9yaWdpbmFsIGxhbmd1YWdlIG9mIHRoZSBhZ3JlZW1lbnQgd2l0aCBVUFMgYXMgc3RhdGVkIG9uIHRoZSBjb25maXJtIHBheW1lbnQgcGFnZSwgYW5kIHRvIGF1dGhvcml6ZSBVUFMgdG8gYWN0IGFzIGZvcndhcmRpbmcgYWdlbnQgZm9yIGV4cG9ydCBjb250cm9sIGFuZCBjdXN0b20gcHVycG9zZXMsIDxiPnNpZ24gYW5kIGRhdGUgaGVyZTo8L2I+Cjwvb2w+CjwvdGQ+Cgo8L3RyPgo8L3RhYmxlPjx0YWJsZSBib3JkZXI9IjEiIGNlbGxwYWRkaW5nPSIyIiBjZWxsc3BhY2luZz0iMCIgd2lkdGg9IjU2NCI+Cjx0cj4KPHRkIGFsaWduPSJsZWZ0IiB2YWxpZ249Im1pZGRsZSIgd2lkdGg9IjQyMyI+PGIgY2xhc3M9InNtYWxsX3RleHQiPiZuYnNwO1NoaXBwZXIncyBTaWduYXR1cmUmbmJzcDs8L2I+CjwvdGQ+Cjx0ZCBhbGlnbj0ibGVmdCIgdmFsaWduPSJtaWRkbGUiPjxiIGNsYXNzPSJzbWFsbF90ZXh0Ij4mbmJzcDtEYXRlIG9mIFNoaXBtZW50Jm5ic3A7PC9iPgo8L3RkPgo8L3RyPgo8dHI+Cjx0ZCBhbGlnbj0ibGVmdCIgaGVpZ2h0PSIyMCI+Jm5ic3A7PGJyPgo8L3RkPgo8dGQgYWxpZ249ImxlZnQiIGhlaWdodD0iMjAiPiZuYnNwOzxicj4KPC90ZD4KPC90cj4KPC90YWJsZT4KPHRhYmxlIGJvcmRlcj0iMCIgY2VsbHBhZGRpbmc9IjAiIGNlbGxzcGFjaW5nPSIwIiB3aWR0aD0iNjAwIj4KPHRyPgo8dGQgY2xhc3M9InNtYWxsX3RleHQiIGFsaWduPSJsZWZ0IiB2YWxpZ249InRvcCI+CiZuYnNwOyZuYnNwOyZuYnNwOwo8YSBuYW1lPSJmb2xkSGVyZSI+Rk9MRCBIRVJFPC9hPjwvdGQ+CjwvdHI+Cjx0cj4KPHRkIGFsaWduPSJsZWZ0IiB2YWxpZ249InRvcCI+PGhyPgo8L3RkPgo8L3RyPgo8L3RhYmxlPgoKPHRhYmxlPgo8dHI+Cjx0ZCBoZWlnaHQ9IjEwIj4mbmJzcDsKPC90ZD4KPC90cj4KPC90YWJsZT4KCjwvZGl2Pgo8dGFibGUgYm9yZGVyPSIwIiBjZWxscGFkZGluZz0iMCIgY2VsbHNwYWNpbmc9IjAiIHdpZHRoPSI2NTAiID48dHI+Cjx0ZCBhbGlnbj0ibGVmdCIgdmFsaWduPSJ0b3AiPgo8SU1HIFNSQz0iLi9sYWJlbDFaNzU4NjNWNjYwMDAwNTIxNi5naWYiIGhlaWdodD0iMzkyIiB3aWR0aD0iNjUxIj4KPC90ZD4KPC90cj48L3RhYmxlPgo8L2JvZHk+CjwvaHRtbD4K";
        String fileSaveFullPath = "G:/waybill/label/2020-12-21/aramex-label.gif";
        ConvertImageUtil.base64GeneratorImage(base64Code, fileSaveFullPath);
    }

    /**
     * 创建fedex label pdf
     * @param xmlFile
     * @return
     */
    public static String buildLabel(File xmlFile) {

        String labelFileFullPath = extractAramexLabelBase64Code(xmlFile);

        return labelFileFullPath;
    }

    /**
     * 创建fedex invoice pdf
     * @param xmlFile
     * @return
     */
    public static String buildInvoice(File xmlFile) {

        String invoiceFileFullPath = extractAramexInvoiceBase64Code(xmlFile);

        return invoiceFileFullPath;
    }

    /**
     * 从xml中提取label  base64编码
     * @param xmlFile
     */
    public static String extractAramexLabelBase64Code(File xmlFile){

        String saveFileFullPath = null;

        try {

            Element rootNode = ConvertImageUtil.createXmlElement(xmlFile);
            String xmlFileAbsolutePath = xmlFile.getAbsolutePath();
            String labelUrl = rootNode.element("Shipments").element("ProcessedShipment").element("ShipmentLabel").element("LabelURL").getText();

            saveFileFullPath = xmlFileAbsolutePath.replaceAll(".xml", "-label.pdf");

            ConvertImageUtil.downloadFromUrl(labelUrl, saveFileFullPath);

        } catch (Exception e) {
            throw new AplException(e.getMessage(), e.getCause().toString());
        }
        //成功
        return saveFileFullPath;
    }

    public static String extractAramexInvoiceBase64Code(File xmlFile){

        String saveFileFullPath;

        try {
            Element rootNode = ConvertImageUtil.createXmlElement(xmlFile);
            String xmlFileAbsolutePath = xmlFile.getAbsolutePath();
            //寻找base64对应的标签
            String base64Data = rootNode.element("LabelImage").element("MultiLabels").element("MultiLabel").element("DocImageVal").getText();

            //组装文件全路径
            saveFileFullPath = xmlFileAbsolutePath.replaceAll(".xml", "-invoice.pdf");
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
