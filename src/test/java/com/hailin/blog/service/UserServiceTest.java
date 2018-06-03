package com.hailin.blog.service;

import com.github.pagehelper.PageInfo;
import com.hailin.BlogApplicationTests;
import com.hailin.blog.model.User;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;

public class UserServiceTest extends BlogApplicationTests{


    @Resource
    private UserService userService;

    @Test
    public void saveUser() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void removeUsersInBatch() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void listUsers() {
    }

    @Test
    public void listUsersByNameLike() {
        PageInfo<User> userPageInfo = userService.listUserAndRolesByNameLike("张" , 3 , 2 , 1);
        logger.info("userPageInfo = {}" ,userPageInfo.toString());

    }

    @Test
    public void listUsersByUsername() {
        UserDetails userDetails = userService.loadUserByUsername("zhangsan");
        System.out.printf(userDetails.toString());
    }
}