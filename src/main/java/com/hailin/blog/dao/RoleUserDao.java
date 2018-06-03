package com.hailin.blog.dao;

import com.hailin.blog.model.RoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleUserDao {

    /**
     * 插入一条用户角色对应的记录
     * @param roleUser
     * @return
     */
    Integer addUserRole(RoleUser roleUser);

    /**
     * 插入一条用户角色对应的记录
     * @param roleUser
     * @return
     */
    Integer updateUserRole(@Param("oldRoleId") Integer oldRoleId ,@Param("roleUser") RoleUser roleUser);

}
