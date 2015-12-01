package com.silence.service.impl;

import com.silence.mapper.extend.NavMapperExtend;
import com.silence.po.Nav;
import com.silence.service.NavService;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    /**
     * 返回所有的系统导航菜单
     * @return
     * @throws Exception
     */
    public List<Nav> findNavList(Map<String,Object> map, Pageable pageable) throws Exception {
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        List<Nav> navList = navMapperExtend.findNavList(userQueryVo);
        if(navList != null){
            return navList;
        }else {
            return null;
        }
    }

    /**
     * 查询菜单的总记录数
     * @return
     * @throws Exception
     */
    public int findNavListSize() throws Exception{
        int size = 0;
        List<Nav> navList = navMapperExtend.findNavIds();
        if(navList != null && navList.size() > 0){
            size = navList.size();
        }
        return size;
    }

    /**
     * 根据页面参数插入导航实体
     */
    @Transactional(readOnly = false)
    public String insertNav(Map<String,Object> map) throws Exception{
        if(map != null){
            Nav nav = new Nav();
            if(map.containsKey("row[state]") && !map.get("row[state]").toString().equals("")){
                nav.setState(map.get("row[state]").toString());
            }
            if(map.containsKey("row[iconCls]")){
                nav.setIconCls(map.get("row[iconCls]").toString());
            }
            if(map.containsKey("row[url]") && !map.get("row[url]").toString().equals("")){
                nav.setUrl(map.get("row[url]").toString());
            }
            if(map.containsKey("row[text]") && !map.get("row[text]").toString().equals("")){
                nav.setText(map.get("row[text]").toString());
            }
            if(map.containsKey("row[nid]") && !map.get("row[nid]").toString().equals("")){
                nav.setNid(Integer.parseInt(map.get("row[nid]").toString()));
            }
            navMapperExtend.insertSelective(nav);
        }
        return "success";
    }

    /**
     * 修改导航信息
     * @param map
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void updateNav(Map<String,Object> map) throws Exception{
        if(map != null && map.containsKey("row[id]")){
            Nav nav = navMapperExtend.selectByPrimaryKey(Integer.parseInt(map.get("row[id]").toString()));
            if(map.containsKey("row[text]") && !map.get("row[text]").toString().equals("")){
                nav.setText(map.get("row[text]").toString());
            }
            if(map.containsKey("row[state]") && !map.get("row[state]").toString().equals("")){
                nav.setState(map.get("row[state]").toString());
            }
            if(map.containsKey("row[iconCls]")){
                nav.setIconCls(map.get("row[iconCls]").toString());
            }
            if(map.containsKey("row[url]")){
                nav.setUrl(map.get("row[url]").toString());
            }
            if(map.containsKey("row[nid]")){
                nav.setNid(Integer.parseInt(map.get("row[nid]").toString()));
            }
            navMapperExtend.updateByPrimaryKeySelective(nav);
        }
    }


    /**
     * 删除导航信息
     * @param id
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public Integer deleteNavById(Integer id) throws Exception {
        int rows = navMapperExtend.deleteByPrimaryKey(id);
        if (rows > 0) {
            return rows;
        }
        return 0;
    }


}
