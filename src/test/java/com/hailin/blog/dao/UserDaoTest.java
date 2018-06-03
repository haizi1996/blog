package com.hailin.blog.dao;

import com.hailin.BlogApplicationTests;
import com.hailin.blog.model.User;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;


public class UserDaoTest extends BlogApplicationTests {

    @Resource
    private UserDao userDao;


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
        User user = userDao.getUserById(1);
        logger.info(user.toString());
    }



    @Test
    public void listUserAndRolesByNameLike() {
        String name = "张";
        List<User> users = userDao.listUserAndRolesByNameLike(name ,  1);
        users.stream().forEach(user -> logger.info(user.toString()));


    }

    @Test
    public void listUsersByUsernames() {
    }
}