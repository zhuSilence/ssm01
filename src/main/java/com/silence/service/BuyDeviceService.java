package com.silence.service;

import com.silence.po.BuyDevice;
import com.silence.utils.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2015/12/2.
 */
public interface BuyDeviceService {
    /**
     * 查询所有购买设备记录
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<BuyDevice> getBuyDeviceList(Map<String,Object> map,Pageable pageable) throws Exception;

    /**
     * 获取符合查询条件的购买设备数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public int getBuyDeviceListSize(Map<String, Object> map, Pageable pageable) throws Exception;


    /**
     * 更新BuyDevice表
     * @param map
     * @throws Exception
     */
    public void updateBuyDevice(Map<String,Object> map) throws Exception;

    /**
     * 新增BuyDevice表
     * @param map
     * @return
     * @throws Exception
     */
    public String insertBuyDevice(Map<String,Object> map) throws Exception;


    /**
     * 删除某一购买设备信息
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteBuyDeviceById(Integer id) throws Exception;
}
