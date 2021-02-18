package com.apl.lms.label.print.bo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author hjr start
 * @Classname UPSInvoiceBo
 * @Date 2021/1/11 16:49
 */
public class UPSInvoiceBo implements Serializable {

    @ApiModelProperty(name = "id", value = "id")
    private Long id;
    //发件人
    @ApiModelProperty(name = "senderCompanyName", value = "发件人公司")
    private String senderCompanyName;

    @ApiModelProperty(name = "senderContact", value = "发件人姓名")
    private String senderContact;

    @ApiModelProperty(name = "senderHouseNumber", value = "发件人门牌号")
    private String senderHouseNumber;

    @ApiModelProperty(name = "senderStreet", value = "发件人街道")
    private String senderStreet;

    @ApiModelProperty(name = "senderAddress1", value = "发件人地址1")
    private String senderAddress1;

    @ApiModelProperty(name = "senderAddress2", value = "发件人地址2")
    private String senderAddress2;

    @ApiModelProperty(name = "senderAddress3", value = "发件人地址3")
    private String senderAddress3;

    @ApiModelProperty(name = "senderCity", value = "发件人城市")
    private String senderCity;

    @ApiModelProperty(name = "senderZipCode", value = "发件人邮编")
    private String senderZipCode;

    @ApiModelProperty(name = "senderCountryCode", value = "发件人国家简码")
    private String senderCountryCode;

    @ApiModelProperty(name = "senderPhone", value = "发件人手机")
    private String senderPhone;

    @ApiModelProperty(name = "senderTel", value = "发件人电话")
    private String senderTel;

    @ApiModelProperty(name = "senderTaxNumber", value = "发件人税号")
    private String senderTaxNumber;

    @ApiModelProperty(name = "senderEori", value = "发件人经营商登记号码")
    private String senderEori;

    //收件人
    @ApiModelProperty(name = "consigneeCompanyName", value = "收件人公司名称")
    private String consigneeCompanyName;

    @ApiModelProperty(name = "consigneeContact", value = "收件人")
    private String consigneeContact;

    @ApiModelProperty(name = "consigneeState", value = "收件人-州")
    private String consigneeState;

    @ApiModelProperty(name = "consigneeCity", value = "收件人城市")
    private String consigneeCity;

    @ApiModelProperty(name = "consigneeAddress1", value = "收件人地址1")
    private String consigneeAddress1;

    @ApiModelProperty(name = "consigneeAddress2", value = "收件人地址2")
    private String consigneeAddress2;

    @ApiModelProperty(name = "consigneeAddress3", value = "收件人地址3")
    private String consigneeAddress3;

    @ApiModelProperty(name = "consigneeCountryCode", value = "收件人国家简码")
    private String consigneeCountryCode;

    @ApiModelProperty(name = "consigneePhone", value = "收件人电话")
    private String consigneePhone;

    @ApiModelProperty(name = "consigneeTel", value = "收件人传真")
    private String consigneeTel;

    @ApiModelProperty(name = "consigneeTaxNumber", value = "收件人税号")
    private String consigneeTaxNumber;

    @ApiModelProperty(name = "consigneeEmail", value = "收件人邮箱")
    private String consigneeEmail;

    @ApiModelProperty(name = "consigneeZipCode", value = "收件人邮编")
    private String consigneeZipCode;

    @ApiModelProperty(name = "consigneeEmail", value = "收件人经营商登记号码")
    private String consigneeEori;

    @ApiModelProperty(name = "waybillCommodity", value = "商品列表")
    private List<WaybillCommodityBo> waybillCommodity;

    public List<WaybillCommodityBo> getWaybillCommodity() {
        return waybillCommodity;
    }

    public void setWaybillCommodity(List<WaybillCommodityBo> waybillCommodity) {
        this.waybillCommodity = waybillCommodity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderCompanyName() {
        return senderCompanyName;
    }

    public void setSenderCompanyName(String senderCompanyName) {
        this.senderCompanyName = senderCompanyName;
    }

    public String getSenderContact() {
        return senderContact;
    }

    public void setSenderContact(String senderContact) {
        this.senderContact = senderContact;
    }

    public String getSenderHouseNumber() {
        return senderHouseNumber;
    }

    public void setSenderHouseNumber(String senderHouseNumber) {
        this.senderHouseNumber = senderHouseNumber;
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSenderAddress1() {
        return senderAddress1;
    }

    public void setSenderAddress1(String senderAddress1) {
        this.senderAddress1 = senderAddress1;
    }

    public String getSenderAddress2() {
        return senderAddress2;
    }

    public void setSenderAddress2(String senderAddress2) {
        this.senderAddress2 = senderAddress2;
    }

    public String getSenderAddress3() {
        return senderAddress3;
    }

    public void setSenderAddress3(String senderAddress3) {
        this.senderAddress3 = senderAddress3;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderZipCode() {
        return senderZipCode;
    }

    public void setSenderZipCode(String senderZipCode) {
        this.senderZipCode = senderZipCode;
    }

    public String getSenderCountryCode() {
        return senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode) {
        this.senderCountryCode = senderCountryCode;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel;
    }

    public String getSenderTaxNumber() {
        return senderTaxNumber;
    }

    public void setSenderTaxNumber(String senderTaxNumber) {
        this.senderTaxNumber = senderTaxNumber;
    }

    public String getSenderEori() {
        return senderEori;
    }

    public void setSenderEori(String senderEori) {
        this.senderEori = senderEori;
    }

    public String getConsigneeCompanyName() {
        return consigneeCompanyName;
    }

    public void setConsigneeCompanyName(String consigneeCompanyName) {
        this.consigneeCompanyName = consigneeCompanyName;
    }

    public String getConsigneeContact() {
        return consigneeContact;
    }

    public void setConsigneeContact(String consigneeContact) {
        this.consigneeContact = consigneeContact;
    }

    public String getConsigneeState() {
        return consigneeState;
    }

    public void setConsigneeState(String consigneeState) {
        this.consigneeState = consigneeState;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeAddress1() {
        return consigneeAddress1;
    }

    public void setConsigneeAddress1(String consigneeAddress1) {
        this.consigneeAddress1 = consigneeAddress1;
    }

    public String getConsigneeAddress2() {
        return consigneeAddress2;
    }

    public void setConsigneeAddress2(String consigneeAddress2) {
        this.consigneeAddress2 = consigneeAddress2;
    }

    public String getConsigneeAddress3() {
        return consigneeAddress3;
    }

    public void setConsigneeAddress3(String consigneeAddress3) {
        this.consigneeAddress3 = consigneeAddress3;
    }

    public String getConsigneeCountryCode() {
        return consigneeCountryCode;
    }

    public void setConsigneeCountryCode(String consigneeCountryCode) {
        this.consigneeCountryCode = consigneeCountryCode;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeTaxNumber() {
        return consigneeTaxNumber;
    }

    public void setConsigneeTaxNumber(String consigneeTaxNumber) {
        this.consigneeTaxNumber = consigneeTaxNumber;
    }

    public String getConsigneeEmail() {
        return consigneeEmail;
    }

    public void setConsigneeEmail(String consigneeEmail) {
        this.consigneeEmail = consigneeEmail;
    }

    public String getConsigneeZipCode() {
        return consigneeZipCode;
    }

    public void setConsigneeZipCode(String consigneeZipCode) {
        this.consigneeZipCode = consigneeZipCode;
    }

    public String getConsigneeEori() {
        return consigneeEori;
    }

    public void setConsigneeEori(String consigneeEori) {
        this.consigneeEori = consigneeEori;
    }
}
