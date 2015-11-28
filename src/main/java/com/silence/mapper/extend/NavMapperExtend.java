package com.silence.mapper.extend;

import com.silence.mapper.NavMapper;
import com.silence.po.Nav;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/28.
 * Desc :
 */
public interface NavMapperExtend extends NavMapper {

    List<Nav> findAllNavsByNid(Integer id) throws Exception;

}
