package com.silence.utils;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/24.
 * Desc :
 */
public class Page<T> {
    private Integer total;
    private List<T> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
