package com.apl.lms.label.print.po;

import java.util.List;

/**
 * @author hjr start
 * @Classname DHLPo
 * @Date 2021/1/8 20:18
 */
public class DHLPo {

    private String airwayBillNumber;

    private String serviceAreaCode;

    private Double packageCharge;

    private List<DHLPiecePo> pieces;

    public String getAirwayBillNumber() {
        return airwayBillNumber;
    }

    public void setAirwayBillNumber(String airwayBillNumber) {
        this.airwayBillNumber = airwayBillNumber;
    }

    public String getServiceAreaCode() {
        return serviceAreaCode;
    }

    public void setServiceAreaCode(String serviceAreaCode) {
        this.serviceAreaCode = serviceAreaCode;
    }

    public Double getPackageCharge() {
        return packageCharge;
    }

    public void setPackageCharge(Double packageCharge) {
        this.packageCharge = packageCharge;
    }

    public List<DHLPiecePo> getPieces() {
        return pieces;
    }

    public void setPieces(List<DHLPiecePo> pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "DHLPo{" +
                "airwayBillNumber='" + airwayBillNumber + '\'' +
                ", serviceAreaCode='" + serviceAreaCode + '\'' +
                ", packageCharge=" + packageCharge +
                ", pieces=" + pieces +
                '}';
    }
}
