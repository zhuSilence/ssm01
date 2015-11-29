package com.silence.controller;

import com.silence.exception.CustomException;
import com.silence.po.User;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * Created by zhuxiang on 2015/11/13.
 * Desc : 系统登录controller
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 初始index页面
     * @return
     */
    @RequestMapping(value = "index.action")
    public String index(){
        return "index";
    }

    /**
     * 用户登录方法
     */
    @RequestMapping(value = "/login.action")
    @ResponseBody public User login(User user, Model model) throws Exception{
        User user1 = userService.getUserByUsernameAndPassword(user);
        if (user1 == null){
            throw new CustomException("用户名或密码错误！");
        }else {
            if(user1.getLocked()){
                //String msg = "<a target=\"_blank\" href=\"http://wpa.qq.com/msgrd?v=3&uin=1347023180&site=qq&menu=yes\"><img border=\"0\" src=\"http://wpa.qq.com/pa?p=2:1347023180:41\" alt=\"联系管理员\" title=\"联系管理员\"/></a>";
                  throw new CustomException("该账户已被锁定，请联系管理员!");
            }else {
                model.addAttribute("user", user);
            }
        }
        return user;
    }

    /**
     *注销方法
     * @return
     */
    @RequestMapping(value = "loginOut.action")
    public String loginOut(){

        return "index";
    }

    @RequestMapping(value = "/home.action")
    public String home(@ModelAttribute("user")User user, Model model){
        if(user != null){
            model.addAttribute("user", user);
        }
        return "home";
    }



}
