package com.silence.service;

import com.silence.po.FixDevice;
import com.silence.utils.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2015/12/2.
 */

public interface FixDeviceService {
    /**
     * 查询所有购买设备记录
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<FixDevice> getFixDeviceList(Map<String,Object> map,Pageable pageable) throws Exception;

    /**
     * 获取符合查询条件的购买设备数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public int getFixDeviceListSize(Map<String, Object> map, Pageable pageable) throws Exception;


    /**
     * 更新FixDevice表
     * @param map
     * @throws Exception
     */
    public void updateFixDevice(Map<String,Object> map) throws Exception;

    /**
     * 新增FixDevice表
     * @param map
     * @return
     * @throws Exception
     */
    public String insertFixDevice(Map<String,Object> map) throws Exception;


    /**
     * 删除某一修理设备信息
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteFixDeviceById(Integer id) throws Exception;
}
