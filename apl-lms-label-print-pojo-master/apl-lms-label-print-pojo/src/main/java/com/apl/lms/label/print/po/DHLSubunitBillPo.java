package com.apl.lms.label.print.po;

import com.apl.lms.label.print.bo.DHLCommonBaseLabelBo;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author hjr start
 * @Classname TestPojo
 * @Date 2020/12/28 15:55
 */

@ApiModel(value = "测试对象", description = "测试对象")
public class DHLSubunitBillPo extends DHLCommonBaseLabelBo implements Serializable {

    private String origin;

    private Integer piece; //1/1

    private String sonBarCodeSn;

    private String sonBarCodeSn1;

    private String sonBarCodeSn2;

    private String sonBarCodeSn3;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public Integer getPiece() {
        return piece;
    }

    @Override
    public void setPiece(Integer piece) {
        this.piece = piece;
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
}
