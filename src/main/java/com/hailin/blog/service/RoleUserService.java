package com.hailin.blog.service;

import com.hailin.blog.model.RoleUser;

public interface RoleUserService {

    /**
     * 插入一条用户角色对应的记录
     * @param roleUser
     * @return
     */
    Integer saveUserRole(RoleUser roleUser);


    Integer updateUserRole(Integer oldRoleId , RoleUser roleUser);

}
