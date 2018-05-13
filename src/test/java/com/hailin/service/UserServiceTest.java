package com.hailin.service;

import com.github.pagehelper.PageInfo;
import com.hailin.BlogApplicationTests;
import com.hailin.model.User;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class UserServiceTest extends BlogApplicationTests{

    static{
        logger = LoggerFactory.getLogger(UserServiceTest.class);
    }
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