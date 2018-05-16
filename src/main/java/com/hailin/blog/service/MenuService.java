package com.hailin.blog.service;

import com.hailin.blog.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {

    /**
     * 展示满足条件的菜单栏 (状态)
     * @param status
     * @return
     */
    List<Menu> listAllMenu(@Param("status") Integer status);

    /**
     * 删除菜单栏
     * @param id
     * @return
     */
    Integer deleteMenuById(@Param("id") Integer id);


    /**
     * 删除菜单栏根据优先级
     * @param priority
     * @return
     */
    Integer deleteMenuByPriority(@Param("priority") Integer priority );

    /**
     * 增加菜单栏
     * @param menu
     * @return
     */
    Integer addMenu(Menu menu);

}
