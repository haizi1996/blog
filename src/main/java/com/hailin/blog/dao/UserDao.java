package com.hailin.blog.dao;

import com.hailin.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 保存用户
     * @param user
     * @return
     */
    Long saveUser(User user);

    /**
     * 删除用户
     * 逻辑删除
     * @param id
     * @return
     */
    Integer removeUser(@Param("id") Integer id , @Param("username") String username);

    /**
     * 删除列表里面的用户
     * @param users
     * @return
     */
    Integer removeUsersInBatch(List<Long> users);

    /**
     * 更新用户
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(@Param("id") Integer id);

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param roleId
     * @param status
     * @return
     */
    List<User> listUserAndRolesByNameLike(@Param("name") String name , @Param("roleId") Integer roleId , @Param("status") int status);

    /**
     * 根据名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(@Param("usernames")Collection<String> usernames);




    /**
     * 查找用户
     * @param userName
     * @param status
     * @return
     */
    User findByUsername(@Param("userName") String userName , @Param("status") Integer status);

    /**
     * 获取用户详情
     * @param userName
     * @return
     */
    User findUserDetailsByUsername(@Param("userName")String userName , @Param("status") Integer status);

}
