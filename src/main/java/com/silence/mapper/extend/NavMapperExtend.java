package com.silence.mapper.extend;

import com.silence.mapper.NavMapper;
import com.silence.po.Nav;
import com.silence.vo.UserQueryVo;

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
     * 根据用户查询条件返回所有的系统导航菜单
     * @return
     * @throws Exception
     */
    public List<Nav> findNavList(UserQueryVo userQueryVo) throws Exception;

    /**
     * 查询出所有的导航对象的id
     * @return
     * @throws Exception
     */
    public List<Nav> findNavIds() throws Exception;

}
