package com.silence.vo;

import com.silence.utils.Pageable;

import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/25.
 * Desc :
 */
public class UserQueryVo {

    //分页对象
    private Pageable pageable;
    //参数map
    private Map<String, Object> map;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
