package com.silence.service;

import com.silence.po.BeUsing;
import com.silence.utils.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
public interface DeviceUsingService {


    /**
     * 查询出所有的设备的使用信息信息
     * @return
     * @throws Exception
     */
    public List<BeUsing> getDeviceUsingList(Map<String, Object> map, Pageable pageable) throws Exception;

    /**
     * 查询出所有的设备使用记录条数
     * @return
     * @throws Exception
     */
    public int getDeviceUsingListSize(Map<String, Object> map, Pageable pageable) throws Exception;

}
