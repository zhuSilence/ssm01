package com.silence.service;

import com.silence.po.Nav;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/28.
 * Desc :
 */
public interface NavService {

    /**
     * 根据传入的nid（父节点id）查询到所有的子节点菜单
     * @param nid
     * @return
     * @throws Exception
     */
    public List<Nav> findNavsByNid(Integer nid) throws Exception;
}
