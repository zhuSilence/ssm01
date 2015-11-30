package com.silence.mapper.extend;

import com.silence.mapper.DeviceMapper;
import com.silence.po.Device;
import com.silence.vo.UserQueryVo;

import java.util.List;

/**
 * Created by Tao on 2015/11/30.
 */
public interface DeviceMapperExtend extends DeviceMapper {

    /**
     * 查询所有符合查询条件设备的集合
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    List<Device> selectDeviceList(UserQueryVo userQueryVo) throws Exception;

    /**
     * 统计查询到的集合的数量
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    int selectAllDeviceListSize(UserQueryVo userQueryVo) throws Exception;
}
