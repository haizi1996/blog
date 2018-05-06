package com.hailin.controller;

import com.hailin.model.Menu;
import com.hailin.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/menus")
    public String listUsers(@RequestParam(value = "status" ,required = false ,defaultValue = "1") Integer status , Model model) {
        List<Menu> menus = menuService.listAllMenu(status);
        model.addAttribute("menus" , menus);
        return "/admins/index";
    }
}
