package com.silence.mapper.extend;

import com.silence.mapper.FixDeviceMapper;
import com.silence.po.FixDevice;
import com.silence.vo.UserQueryVo;

import java.util.List;

/**
 * Created by Tao on 2015/12/2.
 */
public interface FixDeviceMapperExtend extends FixDeviceMapper {
    /**
     * 查询所有符合查询条件修理设备的集合
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    List<FixDevice> selectFixDeviceList(UserQueryVo userQueryVo) throws Exception;

    /**
     * 统计查询到的集合的数量
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    int selectAllFixDeviceListSize(UserQueryVo userQueryVo) throws Exception;
}
