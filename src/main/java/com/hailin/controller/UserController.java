package com.hailin.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailin.dto.Response;
import com.hailin.model.User;
import com.hailin.service.UserService;
import com.hailin.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;


    @RequestMapping("/list")
    public String listUsers(@RequestParam(value = "async" , required = false) boolean async ,
                            @RequestParam(value = "pageIndex" , required = false , defaultValue = "0") Integer pageIndex,
                            @RequestParam(value = "pageSize" ,required = false , defaultValue = "10") Integer pageSize ,
                            @RequestParam(value = "name" , required = false , defaultValue = "") String name ,
                            @RequestParam(value = "status" , required = false ,defaultValue = "1") int status,
                            Model model
    ){
        PageInfo<User> pageInfo = userService.listUsersByNameLike(name , pageIndex ,pageSize , status);
        return JsonUtil.objectToJson(pageInfo);
    }



    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Response deleteUser(@PathVariable("id") Long id ){
        Integer rows = userService.removeUser(id);
        return rows > 0 ? Response.successResponse("删除用户成功"): Response.errorResponse("删除用户失败");
    }

    @GetMapping("/editForm/{id}")
    public String modifyForm(@PathVariable("id") Long id , Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user" , user);
        return "edit";
    }
    @GetMapping("/edit/{id}")
    public Response updateUser(){
     return null;
    }
    @GetMapping("/add/form")
    public String addUserHtml(){
        return "add";
    }
    @PostMapping("/save")
    public String save(User user){
        Optional<User> optionalUser = userService.saveUser(user);
        return null;
    }

}
