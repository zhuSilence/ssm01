package com.silence.po;

public class Device {
    private Integer id;

    private String d_name;

    private String d_desc;

    private Double d_price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name == null ? null : d_name.trim();
    }

    public String getD_desc() {
        return d_desc;
    }

    public void setD_desc(String d_desc) {
        this.d_desc = d_desc == null ? null : d_desc.trim();
    }

    public Double getD_price() {
        return d_price;
    }

    public void setD_price(Double d_price) {
        this.d_price = d_price;
    }
}