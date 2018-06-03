package com.hailin.blog.controller;


import com.hailin.blog.dto.Response;
import com.hailin.blog.enumPackage.RoleEnum;
import com.hailin.blog.model.User;
import com.hailin.blog.service.AuthorityService;
import com.hailin.blog.service.UserService;
import com.hailin.blog.trie.WordTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
//        return "index";
        return "redirect:/blogs";
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
                               Model model , HttpServletRequest request) {

        if (WordTree.getWordTree().existPrimaryKeyWords(user.getUsername())) {
            Response response = Response.errorResponse("该用户名以被其他用户使用");
            model.addAttribute("response" , response);
            return "register";
        }else{
            user.setOperateIp(request.getLocalAddr());
            user.setOperator(user.getUsername());
            Optional<User> newUser = userService.saveUser(user , RoleEnum.BLOGER);
            if(newUser.isPresent()){
                WordTree.getWordTree().addNode(newUser.get().getUsername() ,newUser.get().getId());
                return "redirect:/login";
            }else{
                Response response = Response.errorResponse("注册失败，请正确填写信息");
                model.addAttribute("response" , response);
                return "register";
            }
        }
    }

}
