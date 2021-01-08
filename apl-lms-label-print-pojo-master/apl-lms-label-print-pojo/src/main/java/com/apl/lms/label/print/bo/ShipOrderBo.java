package com.apl.lms.label.print.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author hjr start
 * @Classname WaybillPo
 * @Date 2021/1/6 10:35
 */
@Data
@ApiModel(value = "运单对象", description = "运单对象")
public class ShipOrderBo implements Serializable {

    @ApiModelProperty(name = "orderTime", value = "下单时间")
    private Timestamp orderTime;

    @ApiModelProperty(name = "waybillId", value = "运单id")
    private Long waybillId;

    @ApiModelProperty(name = "trackingSn", value = "转单号")
    private String trackingSn;

    @ApiModelProperty(name = "channelCategory", value = "渠道类型")
    private String channelCategory;

    @ApiModelProperty(name = "receivingTime", value = "收货时间")
    private Date receivingTime;

    @ApiModelProperty(name = "orderOrWaybillType", value = "运单或订单")
    private Integer orderOrWaybillType;

    @ApiModelProperty(name = "isCreateLabel", value = "是否已打印标签")
    private Integer isCreateLabel;

    public Integer getOrderOrWaybillType() {
        if (null == this.orderOrWaybillType || this.orderOrWaybillType < 1)
            this.orderOrWaybillType = 1;
        return this.orderOrWaybillType;
    }
}
