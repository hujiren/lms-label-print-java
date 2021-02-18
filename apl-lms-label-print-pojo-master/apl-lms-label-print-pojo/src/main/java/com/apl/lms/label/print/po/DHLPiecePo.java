package com.apl.lms.label.print.po;

import java.io.Serializable;

/**
 * @author hjr start
 * @Classname DHLPiecePo
 * @Date 2021/1/8 19:40
 */
public class DHLPiecePo implements Serializable {

    private Integer PieceNumber;

    private Double Depth;

    private Double Width;

    private Double Height;

    private Double Weight;

    private String PackageType;

    private Double DimWeight;

    private String PieceContents;

    private String ReferenceID;

    private String DataIdentifier;

    private String LicensePlate;

    private String LicensePlateBarCode;

    @Override
    public String toString() {
        return "DHLPiecePo{" +
                "PieceNumber=" + PieceNumber +
                ", Depth=" + Depth +
                ", Width=" + Width +
                ", Height=" + Height +
                ", Weight=" + Weight +
                ", PackageType='" + PackageType + '\'' +
                ", DimWeight=" + DimWeight +
                ", PieceContents='" + PieceContents + '\'' +
                ", ReferenceID='" + ReferenceID + '\'' +
                ", DataIdentifier='" + DataIdentifier + '\'' +
                ", LicensePlate='" + LicensePlate + '\'' +
                ", LicensePlateBarCode='" + LicensePlateBarCode + '\'' +
                '}';
    }

    public Integer getPieceNumber() {
        return PieceNumber;
    }

    public void setPieceNumber(Integer pieceNumber) {
        PieceNumber = pieceNumber;
    }

    public Double getDepth() {
        return Depth;
    }

    public void setDepth(Double depth) {
        Depth = depth;
    }

    public Double getWidth() {
        return Width;
    }

    public void setWidth(Double width) {
        Width = width;
    }

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double height) {
        Height = height;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public String getPackageType() {
        return PackageType;
    }

    public void setPackageType(String packageType) {
        PackageType = packageType;
    }

    public Double getDimWeight() {
        return DimWeight;
    }

    public void setDimWeight(Double dimWeight) {
        DimWeight = dimWeight;
    }

    public String getPieceContents() {
        return PieceContents;
    }

    public void setPieceContents(String pieceContents) {
        PieceContents = pieceContents;
    }

    public String getReferenceID() {
        return ReferenceID;
    }

    public void setReferenceID(String referenceID) {
        ReferenceID = referenceID;
    }

    public String getDataIdentifier() {
        return DataIdentifier;
    }

    public void setDataIdentifier(String dataIdentifier) {
        DataIdentifier = dataIdentifier;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public String getLicensePlateBarCode() {
        return LicensePlateBarCode;
    }

    public void setLicensePlateBarCode(String licensePlateBarCode) {
        LicensePlateBarCode = licensePlateBarCode;
    }
}
