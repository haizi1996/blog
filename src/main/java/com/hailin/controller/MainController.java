package com.hailin.controller;


import com.hailin.dto.Response;
import com.hailin.model.User;
import com.hailin.service.AuthorityService;
import com.hailin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private AuthorityService authorityService;

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
    public String registerUser(User user ,
                               @RequestParam(value = "status" ,defaultValue = "1" , required = false) int status ,
                               Model model) {
        User u = userService.getUserByUserName(user.getUsername() , status);
        if (u != null) {
            Response response = Response.errorResponse("该用户名以被其他用户使用");
            model.addAttribute("response" , response);
            return "register";
        }else{
            Optional<User> newUser = userService.saveUser(user);
            if(newUser.isPresent()){
                return "redirect:/login";
            }else{
                Response response = Response.errorResponse("注册失败，请正确填写信息");
                model.addAttribute("response" , response);
                return "register";
            }
        }
    }

}
