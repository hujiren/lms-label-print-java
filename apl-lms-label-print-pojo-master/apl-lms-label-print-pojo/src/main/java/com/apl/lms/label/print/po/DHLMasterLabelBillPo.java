package com.apl.lms.label.print.po;

import com.apl.lms.label.print.bo.DHLCommonBaseLabelBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hjr start
 * @Classname TestPojo
 * @Date 2020/12/28 15:55
 */
@ApiModel(value = "主单", description = "主单")
public class DHLMasterLabelBillPo extends DHLCommonBaseLabelBo implements Serializable {

    private String churchyardAirLine;

    private String FRT;

    private String DTP;

    private String termsOfTrade;

    private String declaredValue;//申报价值

    private Integer pieces;//pieces

    public String getChurchyardAirLine() {
        return churchyardAirLine;
    }

    public void setChurchyardAirLine(String churchyardAirLine) {
        this.churchyardAirLine = churchyardAirLine;
    }

    public String getFRT() {
        return FRT;
    }

    public void setFRT(String FRT) {
        this.FRT = FRT;
    }

    public String getDTP() {
        return DTP;
    }

    public void setDTP(String DTP) {
        this.DTP = DTP;
    }

    public String getTermsOfTrade() {
        return termsOfTrade;
    }

    public void setTermsOfTrade(String termsOfTrade) {
        this.termsOfTrade = termsOfTrade;
    }

    public String getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(String declaredValue) {
        this.declaredValue = declaredValue;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }
}
