package com.silence.service.impl;

import com.silence.mapper.extend.NavMapperExtend;
import com.silence.po.Nav;
import com.silence.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/28.
 * Desc :
 */
@Service
@Transactional(readOnly = true)
public class NavManager implements NavService {

    @Autowired
    private NavMapperExtend navMapperExtend;
    /**
     * 根据传入的nid（父节点id）查询到所有的子节点菜单
     * @param nid
     * @return
     * @throws Exception
     */
    public List<Nav> findNavsByNid(Integer nid) throws Exception {
        List<Nav> navList = navMapperExtend.findAllNavsByNid(nid);
        return navList;
    }
}
