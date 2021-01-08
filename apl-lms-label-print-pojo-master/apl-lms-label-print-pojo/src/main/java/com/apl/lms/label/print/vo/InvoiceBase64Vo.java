package com.apl.lms.label.print.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hjr start
 * @Classname InvoiceBase64Vo
 * @Date 2021/1/8 10:47
 */
@Data
@ApiModel(value = "发票base64编码-返回对象", description = "发票base64编码-返回对象")
public class InvoiceBase64Vo implements Serializable {

    @ApiModelProperty(name = "trackingSn", value = "转单号")
    private String trackingSn;

    @ApiModelProperty(name = "base64Code", value = "base64编码")
    private String base64Code;
}
