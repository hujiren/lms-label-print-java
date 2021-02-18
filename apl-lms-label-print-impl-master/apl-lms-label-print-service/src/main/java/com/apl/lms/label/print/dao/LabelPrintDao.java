package com.apl.lms.label.print.dao;

import com.apl.db.adb.AdbHelper;
import com.apl.lms.label.print.bo.ShipOrderBo;
import com.apl.lms.label.print.bo.UPSInvoiceBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;


/**
 * @author hjr start
 * @Classname LabelPrintDao
 * @Date 2021/1/6 10:21
 */
@Repository
public class LabelPrintDao{

    @Autowired
    AdbHelper adbHelper;

    public UPSInvoiceBo getWaybillContacts(Long id, Integer waybillIdOrOrderId){

        StringBuffer sql = new StringBuffer();
        if(waybillIdOrOrderId.equals(1))
            sql.append("select id from apl_lms_waybill_data.waybill_commodity where ship_order_id = " + id);
        else
            sql.append("select id from apl_lms_waybill_data.waybill_commodity where waybill_id = " + id);

        UPSInvoiceBo upsInvoiceBo = adbHelper.queryObj(sql.toString(), null, UPSInvoiceBo.class);
        return upsInvoiceBo;
    }

    /**
     * 获取下单时间和转单号
     * @param id
     * @return
     */
    public ShipOrderBo getShipOrder(Long id, Integer orderIdOrWaybillId) {

        StringBuffer sql = new StringBuffer();
        if(orderIdOrWaybillId.equals(1))
            sql.append("select order_time, waybill_id, channel_category, tracking_sn from apl_lms_order.ship_order where id = " + id);
        else
            sql.append("select order_time, waybill_id, tracking_sn, channel_category from apl_lms_order.ship_order where waybill_id = " + id);
        ShipOrderBo shipOrderBo = adbHelper.queryObj(sql.toString(), null, ShipOrderBo.class);
        return shipOrderBo;
    }


    /**
     * 根据waybillId获取 waybill的收货时间
     * @param waybillId
     * @return
     */
    public Date getWaybillReceivingTimeByWbId(Long waybillId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select receiving_time from apl_lms_waybill.waybill where id = " + waybillId);
        Date receivingTime = adbHelper.queryObj(sql.toString(), null, Date.class);
        return receivingTime;

    }

    /**
     * 根据id更新
     * @param id
     * @param isCreateLabel
     */
    public void updateIsCreateLabelById(Long id, Integer isCreateLabel, Integer orderOrWaybillType) {
        StringBuffer sql = new StringBuffer();
        if(orderOrWaybillType.equals(1))
            sql.append("update apl_lms_order.ship_order set is_create_label = " + isCreateLabel + " where id = " + id);
        else
            sql.append("update apl_lms_order.ship_order set is_create_label = " + isCreateLabel + " where waybill_id = " + id);
        adbHelper.update(sql.toString());
    }
}
