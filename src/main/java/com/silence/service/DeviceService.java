package com.silence.service;

import com.silence.po.Device;
import com.silence.utils.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2015/11/29.
 */
public interface DeviceService {

    /**
     * 查询所有设备记录
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<Device> getDeviceList(Map<String,Object> map,Pageable pageable) throws Exception;

    /**
     * 获取符合查询条件的设备数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public int getDeviceListSize(Map<String, Object> map, Pageable pageable) throws Exception;


    /**
     * 更新Device表
     * @param map
     * @throws Exception
     */
    public void updateDevice(Map<String,Object> map) throws Exception;
}
