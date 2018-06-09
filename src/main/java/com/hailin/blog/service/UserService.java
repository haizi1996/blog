package com.hailin.blog.service;

import com.github.pagehelper.PageInfo;
import com.hailin.blog.enumPackage.RoleEnum;
import com.hailin.blog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    /**
     * 保存用户
     * @param user
     * @return
     */
    Optional<User> saveUser(User user , RoleEnum roleEnum);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer removeUser(Integer id , String username);

    /**
     * 删除列表里面的用户
     * @param ids
     * @return
     */
    Integer removeUsersInBatch(List<Long> ids);

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
    User getUserById(Integer id);


    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    User getUserByUserName(String userName , Integer status);

    /**
     * 获取用户列表
     * @return
     */
    PageInfo<User> listUsers(Integer pageNum , RoleEnum roleEnum, Integer pageSize , Integer status);

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param status
     * @return
     */
    PageInfo<User> listUserAndRolesByNameLike(String name , RoleEnum roleEnum, Integer pageNum , Integer pageSize ,int status);

    /**
     * 根据名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames );
}
