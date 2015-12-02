package com.silence.vo;

import com.silence.po.BeUsing;
import com.silence.po.Device;
import com.silence.utils.Pageable;

import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/25.
 * Desc :
 */
public class UserQueryVo {

    private Device device;

    private BeUsing beUsing;

    //分页对象
    private Pageable pageable;
    //参数map
    private Map<String, Object> map;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public BeUsing getBeUsing() {
        return beUsing;
    }

    public void setBeUsing(BeUsing beUsing) {
        this.beUsing = beUsing;
    }

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
