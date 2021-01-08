package com.apl.lms.label.print.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author hjr start
 * @Classname JasperHelper
 * @Date 2020/12/28 14:41
 */
@Component
public class JasperHelper {
    public static final String PRINT_TYPE = "print";
    public static final String PDF_TYPE = "pdf";
    public static final String EXCEL_TYPE = "excel";
    public static final String HTML_TYPE = "html";
    public static final String WORD_TYPE = "word";
    public static final String PDF_BASE64 = "base64pdf";
    public static String base64pdf = null;

    @Value("${lms.print.reportPath}")
    String reportPath;

    static Logger log = LoggerFactory.getLogger(JasperHelper.class);

    /**
     * 导出pdf，注意此处中文问题，
     * <p>
     * 这里应该详细说：主要在ireport里变下就行了。看图
     * <p>
     * 1）在ireport的classpath中加入iTextAsian.jar 2）在ireport画jrxml时，看ireport最左边有个属性栏。
     * <p>
     * 下边的设置就在点字段的属性后出现。 pdf font name ：STSong-Light ，pdf encoding ：UniGB-UCS2-H
     */
    public void exportPdf(String reportDir, String reportFileName, String exportFilename, Map parameters, List dataSources, HttpServletRequest request,
                          HttpServletResponse response) {

        try {
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSources);

            JasperReport jasperReport = loadReportFile(reportDir, reportFileName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

            response.setContentType("application/pdf");
            if (exportFilename == null || exportFilename.trim().length() == 0) {
                exportFilename = "print.pdf";
            }
            // String fileName = new String(defaultname.getBytes("GBK"),
            // "ISO8859_1");
            // response.setHeader("Content-disposition", "attachment; filename="
            // + fileName);
            ServletOutputStream ouputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            log.error("打印报表异常", e);
            e.printStackTrace();
        }
    }

    public void prepareReport(JasperReport jasperReport, String type) {
        /*
         * 如果导出的是excel，则需要去掉周围的margin
         */
        if ("excel".equals(type))
            try {
                Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("topMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("bottomMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                Field pageHeight = JRBaseReport.class.getDeclaredField("pageHeight");
                pageHeight.setAccessible(true);
                pageHeight.setInt(jasperReport, 2147483647);
            } catch (Exception exception) {
            }
    }

    /**
     * 导出excel
     */
    public void exportExcel(String reportDir, String reportFileName, String exportFilename, Map parameters, List dataSources, HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        /*
         * 设置头信息
         */
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSources);

        JasperReport jasperReport = loadReportFile(reportDir, reportFileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        prepareReport(jasperReport, "excel");

        response.setContentType("application/vnd.ms-excel");
        if (exportFilename == null || exportFilename.trim().length() == 0) {
            exportFilename = "export.xls";
        }

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(exportFilename, "UTF-8") + "\"");

        ServletOutputStream ouputStream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); // 删除记录最下面的空行

        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);// 删除多余的ColumnHeader

        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);// 显示边框
        exporter.exportReport();
        ouputStream.flush();
        ouputStream.close();
    }

    public static enum DocType {
        PDF, HTML, XLS, XML, RTF
    }

    public JRAbstractExporter getJRExporter(DocType docType) {
        JRAbstractExporter exporter = null;
        switch (docType) {
            case PDF:
                exporter = new JRPdfExporter();
                break;
            case HTML:
                exporter = new JRHtmlExporter();
                break;
            case XLS:
                exporter = new JExcelApiExporter();
                break;
            case XML:
                exporter = new JRXmlExporter();
                break;
            case RTF:
                exporter = new JRRtfExporter();
                break;
        }
        return exporter;
    }

    /**
     * 导出html
     */
    public void exportHtml(String reportDir, String reportFileName, Map parameters, List dataSources,
                           HttpServletResponse response) throws Exception {

        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSources);

        JasperReport jasperReport = loadReportFile(reportDir, reportFileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

        response.setContentType("text/html");
        ServletOutputStream ouputStream = response.getOutputStream();
        JRHtmlExporter exporter = new JRHtmlExporter();
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

        exporter.exportReport();

        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 导出word
     */
    public void exportWord(String reportDir, String reportFileName, String exportFilename, Map parameters, List dataSources, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {

        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSources);

        JasperReport jasperReport = loadReportFile(reportDir, reportFileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

        response.setContentType("application/msword;charset=utf-8");
        String defaultname = null;
        if (exportFilename == null || exportFilename.trim().length() == 0) {
            defaultname = "export.doc";
        }
        String fileName = new String(defaultname.getBytes("GBK"), "utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        JRExporter exporter = new JRRtfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

        exporter.exportReport();
    }


    JasperReport loadReportFile(String reportDir, String reportFileName) throws Exception {

        File file = null;
        if (null != reportDir && reportDir.trim().length() > 0) {
            file = new File(reportPath + "/" + reportDir + "/" + reportFileName);
        }
        if (null == file || !file.exists()) {
            file = new File(reportPath + "/common/" + reportFileName);
        }

        if (!file.exists()) {
            throw new Exception("report file not exists!");
        }

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

        return jasperReport;
    }
}