package com.silence.po;

public class BeUsing {
    private Integer id;

    private Integer d_id;

    private String u_place;

    private Integer u_state;

    private String u_mark;

    private Boolean is_using;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public String getU_place() {
        return u_place;
    }

    public void setU_place(String u_place) {
        this.u_place = u_place == null ? null : u_place.trim();
    }

    public Integer getU_state() {
        return u_state;
    }

    public void setU_state(Integer u_state) {
        this.u_state = u_state;
    }

    public String getU_mark() {
        return u_mark;
    }

    public void setU_mark(String u_mark) {
        this.u_mark = u_mark == null ? null : u_mark.trim();
    }

    public Boolean getIs_using() {
        return is_using;
    }

    public void setIs_using(Boolean is_using) {
        this.is_using = is_using;
    }
}