package com.hailin.blog.service.impl;

import com.hailin.blog.dao.MenuDao;
import com.hailin.blog.model.Menu;
import com.hailin.blog.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> listAllMenu(Integer status) {
        return menuDao.listAllMenu(status);
    }

    @Override
    public Integer deleteMenuById(Integer id) {
        return menuDao.deleteMenuById(id);
    }

    @Override
    public Integer deleteMenuByPriority(Integer priority) {
        return menuDao.deleteMenuByPriority(priority);
    }

    @Override
    public Integer addMenu(Menu menu) {
        return menuDao.addMenu(menu);
    }
}
