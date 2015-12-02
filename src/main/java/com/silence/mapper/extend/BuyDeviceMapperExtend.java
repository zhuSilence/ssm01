package com.silence.mapper.extend;

import com.silence.mapper.BuyDeviceMapper;
import com.silence.po.BuyDevice;
import com.silence.vo.UserQueryVo;

import java.util.List;

/**
 * Created by Tao on 2015/12/2.
 */
public interface BuyDeviceMapperExtend extends BuyDeviceMapper {
    /**
     * 查询所有符合查询条件购买设备的集合
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    List<BuyDevice> selectBuyDeviceList(UserQueryVo userQueryVo) throws Exception;

    /**
     * 统计查询到的集合的数量
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    int selectAllBuyDeviceListSize(UserQueryVo userQueryVo) throws Exception;
}
