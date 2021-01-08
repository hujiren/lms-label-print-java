package com.apl.lms.label.print.service;

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

}
