package com.silence.service;

import com.silence.po.Nav;
import com.silence.utils.Pageable;

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


    /**
     * 返回所有的系统导航菜单
     * @return
     * @throws Exception
     */
    public List<Nav> findNavList(Map<String,Object> map, Pageable pageable) throws Exception;

    /**
     * 查询菜单的总记录数
     * @return
     * @throws Exception
     */
    public int findNavListSize() throws Exception;

    /**
     * 根据页面参数插入导航实体
     */
    public String insertNav(Map<String,Object> map) throws Exception;

    /**
     * 修改导航信息
     * @param map
     * @throws Exception
     */
    public void updateNav(Map<String,Object> map) throws Exception ;

    /**
     * 删除导航信息
     * @param id
     * @throws Exception
     */
    public Integer deleteNavById(Integer id) throws Exception ;

}
