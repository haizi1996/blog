package com.hailin.blog.dao;

import com.hailin.blog.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {


    Integer saveRole(Role role);

}
