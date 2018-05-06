package com.hailin.service;

import com.github.pagehelper.PageInfo;
import com.hailin.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * 保存用户
     * @param user
     * @return
     */
    Optional<User> saveUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer removeUser(Long id);

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
    User updateUser(User user);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    PageInfo<User> listUsers(Integer pageNum , Integer pageSize , int status);

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param status
     * @return
     */
    PageInfo<User> listUsersByNameLike(String name , Integer pageNum , Integer pageSize ,int status);

    /**
     * 更具名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames);
}
