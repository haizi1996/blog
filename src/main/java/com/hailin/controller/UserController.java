package com.hailin.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hailin.enumPackage.AuthorityEmun;
import com.hailin.dto.Response;
import com.hailin.model.Authority;
import com.hailin.model.User;
import com.hailin.service.AuthorityService;
import com.hailin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private AuthorityService authorityService;


    @RequestMapping("/list")
    @ResponseBody
    public ModelAndView listUsers(@RequestParam(value = "async", required = false , defaultValue = "false") boolean async ,
                            @RequestParam(value = "pageIndex" , required = false , defaultValue = "0") Integer pageIndex,
                            @RequestParam(value = "pageSize" ,required = false , defaultValue = "3") Integer pageSize ,
                            @RequestParam(value = "name" , required = false , defaultValue = "") String name ,
                            @RequestParam(value = "status" , required = false ,defaultValue = "1") int status,
                            Model model
    ){
        PageInfo<User> pageInfo = userService.listUsersByNameLike(name , pageIndex ,pageSize , status);
        Response response = pageInfo != null ? Response.successResponse(pageInfo) : Response.errorResponse("");
        model.addAttribute("listUserResponse" , response);
        ModelAndView modelAndView = new ModelAndView(async ?"users/list :: #mainContainer" :"users/list","userModel", model);
        return modelAndView;
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
    public String saveOrUpdateUser(User user , @RequestParam(value = "AuthorityId" , required = true) Long authorityId){
        Optional<User> optionalUser = userService.saveUser(user);
        List<Authority> authorities = Lists.newArrayList(authorityService.getAuthorityById(authorityId , AuthorityEmun.AuthorityID.ROLE_USER_AUTHORITY_ID.getCode()));
        if(optionalUser.isPresent()){
            optionalUser.get().setAuthorities(authorities);
        }

        return null;
    }

}
