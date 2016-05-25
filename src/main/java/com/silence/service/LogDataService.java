package com.silence.service;

import com.silence.po.Logdata;
import com.silence.utils.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/29.
 */
public interface LogDataService {

    /**
     * 查询所有设备记录
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<Logdata> getLogDataList(Map<String, Object> map, Pageable pageable) throws Exception;

    /**
     * 获取符合查询条件的设备数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public int getLogDataListSize(Map<String, Object> map, Pageable pageable) throws Exception;


    /**
     * 更新Device表
     * @param map
     * @throws Exception
     */
    public void updateLogData(Map<String, Object> map) throws Exception;

    /**
     * 新增Device表
     * @param map
     * @return
     * @throws Exception
     */
    public String insertLogData(Map<String, Object> map) throws Exception;


    /**
     * 删除某一设备信息
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteLogDataById(Integer id) throws Exception;
}
