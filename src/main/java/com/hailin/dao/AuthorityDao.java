package com.hailin.dao;

import com.hailin.model.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 权限验证dao
 */
@Mapper
public interface AuthorityDao {

    Authority findOneById(@Param("id") Integer id , @Param("status") int status);

    Authority findAuthorityByUserName(@Param("userName") String userName, @Param("status") int status);

    Integer addAuthority(Authority authority);

}
