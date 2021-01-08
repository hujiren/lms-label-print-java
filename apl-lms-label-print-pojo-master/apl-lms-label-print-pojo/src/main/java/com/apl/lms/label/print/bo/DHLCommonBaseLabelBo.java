package com.apl.lms.label.print.bo;

import java.io.Serializable;
import java.util.List;
/**
 * @author hjr start
 * @Classname DHLcommonBarCodePojo
 * @Date 2020/12/30 15:10
 */
public class DHLCommonBaseLabelBo implements Serializable {

    private String printTime;

    private String packageType;

    private String shipperCompanyName;

    private String shipperName;

    private List<String> addressLine;

    private String addressLine2;

    private String shipperCity;

    private String divisionCode;

    private String shipperContact;//联系方式

    private String receiverName;

    private List<String> receiverAddressLine;

    private String receiverAddressLine2;

    private String receiverCity;

    private String receiverCountryName;

    private String receiverContact;//联系方式

    private String overseasAirLine;

    private String referenceId;// Ref: KFH1680983662

    private Double weight;//

    private String pieceContents;//MK3VENTURITUBE

    private Integer piece;

    private List<String> barCodeSn;//

    private String licensePlate;

    private List<String> dataIdentifier;//前缀

    private String barCodeSn2;//前缀+barCodeSn

    private String productShortName;//express worldwide

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

    public List<String> getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(List<String> addressLine) {
        this.addressLine = addressLine;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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

    public List<String> getReceiverAddressLine() {
        return receiverAddressLine;
    }

    public void setReceiverAddressLine(List<String> receiverAddressLine) {
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

    public List<String> getBarCodeSn() {
        return barCodeSn;
    }

    public void setBarCodeSn(List<String> barCodeSn) {
        this.barCodeSn = barCodeSn;
    }

    public List<String> getDataIdentifier() {
        return dataIdentifier;
    }

    public void setDataIdentifier(List<String> dataIdentifier) {
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
