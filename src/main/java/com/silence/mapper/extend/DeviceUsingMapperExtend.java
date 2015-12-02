package com.silence.mapper.extend;

import com.silence.mapper.BeUsingMapper;
import com.silence.po.BeUsingVo;
import com.silence.vo.UserQueryVo;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/30.
 * Desc :
 */
public interface DeviceUsingMapperExtend extends BeUsingMapper {

    //根据页面用户的查询条件动态查询设备使用信息列表
    List<BeUsingVo> selectBeUsingList(UserQueryVo userQueryVo) throws Exception;

    //根据页面用户的查询条件动态查询设备使用信息列表的长度，用于分页
    int selectAllBeUsingListSize(UserQueryVo userQueryVo) throws Exception;

}
