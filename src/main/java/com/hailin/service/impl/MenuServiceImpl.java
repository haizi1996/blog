package com.hailin.service.impl;

import com.hailin.dao.MenuDao;
import com.hailin.model.Menu;
import com.hailin.service.MenuService;

import javax.annotation.Resource;
import java.util.List;

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
