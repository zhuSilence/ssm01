package com.silence.po;

/**
 * Created by zhuxiang on 2015/12/2.
 * Desc :
 */
public class BeUsingVo extends BeUsing {
    private String d_name;
    private String d_desc;

    private Double d_price;
    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_desc() {
        return d_desc;
    }

    public void setD_desc(String d_desc) {
        this.d_desc = d_desc;
    }

    public Double getD_price() {
        return d_price;
    }

    public void setD_price(Double d_price) {
        this.d_price = d_price;
    }
}
