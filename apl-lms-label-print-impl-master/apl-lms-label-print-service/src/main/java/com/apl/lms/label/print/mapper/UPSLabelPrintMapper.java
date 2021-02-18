package com.apl.lms.label.print.mapper;

import com.apl.lms.label.print.bo.UPSInvoiceBo;
import com.apl.lms.label.print.bo.WaybillCommodityBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hjr start
 * @Classname UPSLabelPrintMapper
 * @Date 2021/1/12 10:06
 */
@Mapper
@Repository
public interface UPSLabelPrintMapper {

    UPSInvoiceBo getWaybillContacts(Long id);

    List<WaybillCommodityBo> getWaybillCommodityList(Long id);
}
