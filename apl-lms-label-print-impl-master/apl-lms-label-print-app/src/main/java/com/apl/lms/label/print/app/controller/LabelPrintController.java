package com.apl.lms.label.print.app.controller;

import com.apl.lms.label.print.service.LabelPrintService;
import com.apl.lms.label.print.vo.InvoiceBase64Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author hjr start
 * @Classname TestPrint
 * @Date 2020/12/28 15:08
 */
@RestController
@RequestMapping("/print")
@Api(value = "打印DHL运单",tags = "打印DHL运单")
@Slf4j
public class LabelPrintController {

    LabelPrintService labelPrintService;

    public LabelPrintController(LabelPrintService labelPrintService){
        this.labelPrintService = labelPrintService;
    }


    @GetMapping("/print-label")
    @ApiOperation(value = "打印label", notes = "打印label")
    @ApiImplicitParam(name = "id", value = "运单id/订单id", required = true, paramType = "query")
    public void printLabel(@NotNull(message = "运单id不能为空") @Min(value = 0, message = "最小值为0") Long id,
                           HttpServletResponse response){

        labelPrintService.printLabel(response, id, 1);
    }

    @GetMapping("/print-invoice")
    @ApiOperation(value = "打印发票", notes = "打印发票")
    @ApiImplicitParam(name = "id", value = "运单id/订单id", required = true, paramType = "query")
    public void printInvoiceL(@NotNull(message = "运单id不能为空") @Min(value = 0, message = "最小值为0") Long id, HttpServletResponse response){
        labelPrintService.printLabel(response, id, 2);
    }

    @PostMapping("/get-invoice-base64")
    @ApiOperation(value = "获取发票base64信息", notes = "获取发票base64信息")
    @ApiImplicitParam(name = "id", value = "运单id/订单id", required = true, paramType = "query")
    public InvoiceBase64Vo getInvoiceBase64(@NotNull(message = "运单id不能为空") @Min(value = 0, message = "最小值为0") Long id){
         return labelPrintService.getInvoiceBase64(id);
    }
}
