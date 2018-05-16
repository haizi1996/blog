package com.hailin.blog.controller;

import com.hailin.blog.dto.Response;
import com.hailin.blog.model.Menu;
import com.hailin.blog.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Resource
    private MenuService menuService;


    /**
     * 获取后台管理主页面
     * @return
     */
    @GetMapping("/index")
    public String listMenus(@RequestParam(value = "status" ,required = false ,defaultValue = "1") Integer status , Model model) {
        List<Menu> menus = menuService.listAllMenu(status);
        Response response = CollectionUtils.isEmpty(menus) ? Response.errorResponse("获取菜单栏失败"): Response.successResponse(menus);
        model.addAttribute("response" , response);
        return "admins/index";
    }
}
