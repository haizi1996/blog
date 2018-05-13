package com.hailin.dao;

import com.hailin.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {


    Integer saveRole(Role role);

}
