package com.silence.controller;

import com.silence.po.Nav;
import com.silence.service.NavService;
import com.silence.utils.Page;
import com.silence.utils.Pageable;
import com.silence.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/28.
 * Desc :
 */
@Controller
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private NavService navService;

    /**
     * 根据页面传入的nid的值查询到所有的字节点的系统菜单
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/getAllNavs.action")
    @ResponseBody public List<Nav> getAllNavs(HttpServletRequest request) throws Exception {
        String nid = request.getParameter("id");
        List<Nav> navList;
        if(nid != null){
            navList = navService.findNavsByNid(Integer.parseInt(nid));
        }else {
            navList = navService.findNavsByNid(0);
        }
        return navList;
    }


    /**
     * 查询到所有的字节点的系统菜单
     * @throws Exception
     */
    @RequestMapping(value = "/getNavList.action")
    @ResponseBody public Object getNavList(HttpServletRequest request,Pageable pageable) throws Exception {
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage()-1)*pageable.getRows());
        List<Nav> navList = navService.findNavList(map,pageable);
        if(navList != null){
            Page<Nav> page = new Page<Nav>();
            page.setTotal(navService.findNavListSize());
            page.setRows(navList);
            return page;
        }else {
            return null;
        }
    }


    /**
     * 新增导航信息
     * @return
     */
    @RequestMapping(value = "/insertNav.action", method = RequestMethod.POST)
    @ResponseBody public String insertNav(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        if(map != null){
            return navService.insertNav(map);
        }else {
            return "error";
        }
    }

    /**
     * 根据前台传入的id，对指定的导航进行修改
     * @return
     */
    @RequestMapping(value = "/updateNav.action", method = RequestMethod.POST)
    @ResponseBody public String updateNav(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        navService.updateNav(map);
        return "success";
    }


    /**
     * 根据前台传入的ids，对指定的导航进行删除
     * @return
     */
    @RequestMapping(value = "/deleteNav.action", method = RequestMethod.POST)
    @ResponseBody public String deleteNav(HttpServletRequest request) throws Exception{
        String ids = request.getParameter("ids");
        String[] stringIds = ids.split(",");
        Integer[] integerIds = new Integer[stringIds.length];
        for (int i = 0; i < stringIds.length; i++){
            integerIds[i] = Integer.parseInt(stringIds[i]);
            navService.deleteNavById(integerIds[i]);
        }
        return "success";
    }

    /**
     * 跳转到用户列表的页面
     * @return
     */
    @RequestMapping(value = "/userList.action")
    public String userList(){
        return "userList";
    }


    /**
     * 查询所有的系统菜单
     * @return
     */
    @RequestMapping(value = "/navList.action")
    public String navList(){
        return "navList";
    }

    /**
     * 跳转到设备使用列表页面
     * @return
     */
    @RequestMapping(value = "/usingList.action")
    public String usingList(){
        return "usingList";
    }

    /**
     * 跳转到设备列表页面
     * @return
     */
    @RequestMapping(value = "/deviceList.action")
    public String deviceList(){
        return "deviceList";
    }

    /**
     * 跳转到购买设备列表页面
     * @return
     */
    @RequestMapping(value = "/buydevice.action")
    public String buydevice(){
        return "buyDevice";
    }


    @RequestMapping(value = "/logData.action")
    public String logData(){
        return "logData";
    }

    /**
     * 跳转到修理设备列表页面
     * @return
     */
    @RequestMapping(value = "/fixdevice.action")
    public String fixdevice(){
        return "fixDevice";
    }
}
