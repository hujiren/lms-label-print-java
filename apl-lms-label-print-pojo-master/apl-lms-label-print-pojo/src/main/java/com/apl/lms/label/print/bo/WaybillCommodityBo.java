package com.apl.lms.label.print.bo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hjr start
 * @Classname WaybillCommodityBo
 * @Date 2021/1/12 14:40
 */
public class WaybillCommodityBo implements Serializable {

    @ApiModelProperty(name = "description", value = "货物详细描述")
    private String description;

    @ApiModelProperty(name = "hsCode", value = "商品型号")
    private String hsCode;

    @ApiModelProperty(name = "qty", value = "商品数量")
    private Integer qty;

    @ApiModelProperty(name = "unitPrice", value = "单价")
    private Double unitPrice;

    @ApiModelProperty(name = "unitWeight", value = "单重")
    private Double unitWeight;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(Double unitWeight) {
        this.unitWeight = unitWeight;
    }
}
