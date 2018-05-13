package com.hailin.service;

import com.hailin.model.RoleUser;

public interface RoleUserService {

    /**
     * 插入一条用户角色对应的记录
     * @param roleUser
     * @return
     */
    Integer saveUserRole(RoleUser roleUser);

}
