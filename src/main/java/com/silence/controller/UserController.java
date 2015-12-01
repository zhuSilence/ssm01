package com.silence.controller;

import com.alibaba.fastjson.JSON;
import com.silence.po.User;
import com.silence.service.UserService;
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
 * Created by zhuxiang on 2015/11/24.
 * Desc :
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 接收页面查询参数和分页条件进行相应的数据查询
     * @param request
     * @param pageable
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserList.action", method = RequestMethod.POST)
    @ResponseBody public Object getUserList(HttpServletRequest request,Pageable pageable) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage()-1)*pageable.getRows());
        List<User> userList = userService.getUserList(map,pageable);
        if (userList != null && userList.size() > 0) {
            Page<User> page = new Page<User>();
            page.setTotal(userService.getUserListSize(map,pageable));
            page.setRows(userList);
            //将生成的json字符串中的boolean的true和false替换为字符串的“true”和“false”
            //否则前台的checkbox不能自动的选中
            return JSON.toJSONString(page).replace("true","\"true\"").replace("false","\"false\"");
        }else {
            return null;
        }
    }


    /**
     * 根据前台传入的ids，对指定的用户进行删除
     * @return
     */
    @RequestMapping(value = "/deleteUser.action", method = RequestMethod.POST)
    @ResponseBody public String deleteUser(HttpServletRequest request) throws Exception{
        String ids = request.getParameter("ids");
        String[] stringIds = ids.split(",");
        Integer[] integerIds = new Integer[stringIds.length];
        for (int i = 0; i < stringIds.length; i++){
            integerIds[i] = Integer.parseInt(stringIds[i]);
            userService.deleteUserById(integerIds[i]);
        }
        return "success";
    }


    /**
     * 新增用户信息
     * @return
     */
    @RequestMapping(value = "/insertUser.action", method = RequestMethod.POST)
    @ResponseBody public String insertUser(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        if(map != null){
            return userService.insertUser(map);
        }else {
            return "error";
        }
    }


    /**
     * 根据前台传入的id，对指定的用户进行修改
     * @return
     */
    @RequestMapping(value = "/updateUser.action", method = RequestMethod.POST)
    @ResponseBody public String updateUser(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        userService.updateUser(map);
        return "success";
    }
}
