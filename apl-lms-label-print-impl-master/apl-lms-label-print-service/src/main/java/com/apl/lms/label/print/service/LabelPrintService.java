package com.apl.lms.label.print.service;

import com.apl.lms.label.print.vo.InvoiceBase64Vo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hjr start
 * @Classname TestPrintService
 * @Date 2020/12/28 15:10
 */

public interface LabelPrintService {

    /**
     * 打印D运单
     * @param id 运单id/订单id
     * @param response
     */
    void printLabel(HttpServletResponse response, Long id, Integer docType);

    /**
     * 返回发票base64编码
     * @param id
     * @return
     */
    InvoiceBase64Vo getInvoiceBase64(Long id);
}
