package com.hailin.controller;


import com.hailin.dto.Response;
import com.hailin.model.User;
import com.hailin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }




    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 获取登录界面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/login-error")
    public String loginError(Model model) {
        Response response = Response.errorResponse("登陆失败，账号或者密码错误！");
        model.addAttribute("msg", response);
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }


    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        return "redirect:/login";
    }

}
