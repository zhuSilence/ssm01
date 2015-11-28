package com.silence.controller;

import com.silence.po.Nav;
import com.silence.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 跳转到用户列表的页面
     * @return
     */
    @RequestMapping(value = "/userList.action")
    public String userList(){
        return "userList";
    }
}
