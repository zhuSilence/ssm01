package com.silence.po;

import java.util.Date;

public class FixDevice {
    private Integer id;

    private String fixer;

    private Integer d_id;

    private Date fix_time;

    private String fix_mark;

    private Boolean is_fixed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFixer() {
        return fixer;
    }

    public void setFixer(String fixer) {
        this.fixer = fixer == null ? null : fixer.trim();
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public Date getFix_time() {
        return fix_time;
    }

    public void setFix_time(Date fix_time) {
        this.fix_time = fix_time;
    }

    public String getFix_mark() {
        return fix_mark;
    }

    public void setFix_mark(String fix_mark) {
        this.fix_mark = fix_mark == null ? null : fix_mark.trim();
    }

    public Boolean getIs_fixed() {
        return is_fixed;
    }

    public void setIs_fixed(Boolean is_fixed) {
        this.is_fixed = is_fixed;
    }
}