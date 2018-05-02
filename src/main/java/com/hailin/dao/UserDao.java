package com.hailin.dao;

import com.github.pagehelper.Page;
import com.hailin.model.User;
import com.hailin.pagehelper.Pageable;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface UserDao {
    /**
     * 保存用户
     * @param user
     * @return
     */
    Long saveUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer removeUser(@Param("id") Long id);

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
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);

    /**
     * 根据名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames);


}
