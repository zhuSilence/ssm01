package com.silence.mapper.extend;

import com.silence.mapper.NavMapper;
import com.silence.po.Nav;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/28.
 * Desc :
 */
public interface NavMapperExtend extends NavMapper {

    /**
     * 根据传入的nid（父节点id）查询到所有的子节点菜单
     * @return
     * @throws Exception
     */
    List<Nav> findAllNavsByNid(Integer id) throws Exception;

    /**
     * 返回所有的系统导航菜单
     * @return
     * @throws Exception
     */
    public List<Nav> findNavList() throws Exception;

}
