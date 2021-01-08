package com.apl.lms.label.print.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author hjr start
 * @Classname DHLcommonBarCodePojo
 * @Date 2020/12/30 15:10
 */
public class DHLCommonBaseLabelBo2 implements Serializable {

    private String printTime;

    private String packageType;

    private String shipperCompanyName;

    private String shipperName;

    private String shipperAddressLine1;

    private String shipperAddressLine2;

    private String shipperAddressLine3;//List<String>

    private String shipperCity;

    private String divisionCode;

    private String shipperContact;//联系方式

    private String receiverName;

    private String receiverAddressLine;//List<String>

    private String receiverAddressLine2;

    private String receiverCity;

    private String receiverCountryName;

    private String receiverContact;//联系方式

    private String overseasAirLine;

    private String referenceId;// Ref: KFH1680983662

    private Double weight;//

    private String pieceContents;//MK3VENTURITUBE

    private Integer piece;

    private String barCodeSn;//List<String>

    private String licensePlate;

    private String dataIdentifier;//前缀 List<String>

    private String barCodeSn2;//前缀+barCodeSn

    private String productShortName;//express worldwide

    private String origin;

    private String currencyCode;

    private String sonBarCodeSn;

    private String sonBarCodeSn1;

    private String sonBarCodeSn2;

    private String sonBarCodeSn3;

    private String churchyardAirLine;

    private String FRT;

    private String DTP;

    private String termsOfTrade;

    private String declaredValue;//申报价值

    private Integer pieces;//pieces

    public String getShipperAddressLine1() {
        return shipperAddressLine1;
    }

    public void setShipperAddressLine1(String shipperAddressLine1) {
        this.shipperAddressLine1 = shipperAddressLine1;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getShipperCompanyName() {
        return shipperCompanyName;
    }

    public void setShipperCompanyName(String shipperCompanyName) {
        this.shipperCompanyName = shipperCompanyName;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperAddressLine3() {
        return shipperAddressLine3;
    }

    public void setShipperAddressLine3(String shipperAddressLine3) {
        this.shipperAddressLine3 = shipperAddressLine3;
    }

    public String getShipperAddressLine2() {
        return shipperAddressLine2;
    }

    public void setShipperAddressLine2(String shipperAddressLine2) {
        this.shipperAddressLine2 = shipperAddressLine2;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getShipperContact() {
        return shipperContact;
    }

    public void setShipperContact(String shipperContact) {
        this.shipperContact = shipperContact;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddressLine() {
        return receiverAddressLine;
    }

    public void setReceiverAddressLine(String receiverAddressLine) {
        this.receiverAddressLine = receiverAddressLine;
    }

    public String getReceiverAddressLine2() {
        return receiverAddressLine2;
    }

    public void setReceiverAddressLine2(String receiverAddressLine2) {
        this.receiverAddressLine2 = receiverAddressLine2;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCountryName() {
        return receiverCountryName;
    }

    public void setReceiverCountryName(String receiverCountryName) {
        this.receiverCountryName = receiverCountryName;
    }

    public String getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(String receiverContact) {
        this.receiverContact = receiverContact;
    }

    public String getOverseasAirLine() {
        return overseasAirLine;
    }

    public void setOverseasAirLine(String overseasAirLine) {
        this.overseasAirLine = overseasAirLine;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPieceContents() {
        return pieceContents;
    }

    public void setPieceContents(String pieceContents) {
        this.pieceContents = pieceContents;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public String getBarCodeSn() {
        return barCodeSn;
    }

    public void setBarCodeSn(String barCodeSn) {
        this.barCodeSn = barCodeSn;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDataIdentifier() {
        return dataIdentifier;
    }

    public void setDataIdentifier(String dataIdentifier) {
        this.dataIdentifier = dataIdentifier;
    }

    public String getBarCodeSn2() {
        return barCodeSn2;
    }

    public void setBarCodeSn2(String barCodeSn2) {
        this.barCodeSn2 = barCodeSn2;
    }

    public String getProductShortName() {
        return productShortName;
    }

    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSonBarCodeSn() {
        return sonBarCodeSn;
    }

    public void setSonBarCodeSn(String sonBarCodeSn) {
        this.sonBarCodeSn = sonBarCodeSn;
    }

    public String getSonBarCodeSn1() {
        return sonBarCodeSn1;
    }

    public void setSonBarCodeSn1(String sonBarCodeSn1) {
        this.sonBarCodeSn1 = sonBarCodeSn1;
    }

    public String getSonBarCodeSn2() {
        return sonBarCodeSn2;
    }

    public void setSonBarCodeSn2(String sonBarCodeSn2) {
        this.sonBarCodeSn2 = sonBarCodeSn2;
    }

    public String getSonBarCodeSn3() {
        return sonBarCodeSn3;
    }

    public void setSonBarCodeSn3(String sonBarCodeSn3) {
        this.sonBarCodeSn3 = sonBarCodeSn3;
    }

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
