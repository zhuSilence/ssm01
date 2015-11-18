package com.silence.po;

import java.util.Date;

public class BuyDevice {
    private Integer id;

    private String buyer;

    private Integer d_id;

    private Double b_money;

    private Date b_time;

    private String b_mark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public Double getB_money() {
        return b_money;
    }

    public void setB_money(Double b_money) {
        this.b_money = b_money;
    }

    public Date getB_time() {
        return b_time;
    }

    public void setB_time(Date b_time) {
        this.b_time = b_time;
    }

    public String getB_mark() {
        return b_mark;
    }

    public void setB_mark(String b_mark) {
        this.b_mark = b_mark == null ? null : b_mark.trim();
    }
}