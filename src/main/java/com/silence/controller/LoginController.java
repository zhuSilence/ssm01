package com.silence.controller;

import com.silence.exception.CustomException;
import com.silence.po.User;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录方法
     */
    @RequestMapping("/login.action")
    public String login(User user, Model model) throws Exception{

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        User user1 = userService.getUserById(1);
        if (user1 == null){
            throw new CustomException("不存在该用户的信息");
        }else {
            user1.setSalt("haha");
            userService.updateUser(user1);
        }

        /*Device device = new Device();
        device.setD_desc("路由器");
        device.setD_name("路由器");
        device.setD_price(5.2f);*/

        model.addAttribute("username",user1.getUsername());
        return "home";
    }

}
