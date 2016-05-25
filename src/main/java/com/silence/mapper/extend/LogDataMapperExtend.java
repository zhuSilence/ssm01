package com.silence.mapper.extend;

import com.silence.mapper.LogdataMapper;
import com.silence.po.Logdata;
import com.silence.vo.UserQueryVo;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/30.
 */
public interface LogDataMapperExtend extends LogdataMapper {


    List<Logdata> selectLogDataList(UserQueryVo userQueryVo) throws Exception;

    /**
     * 统计查询到的集合的数量
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    int selectAllDeviceListSize(UserQueryVo userQueryVo) throws Exception;
}
