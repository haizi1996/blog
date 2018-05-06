package com.hailin.dao;

import com.hailin.BlogApplicationTests;
import com.hailin.model.User;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;


public class UserDaoTest extends BlogApplicationTests {

    @Resource
    private UserDao userDao;

    static {
        logger = LoggerFactory.getLogger(UserDaoTest.class);
    }


    @Test
    public void saveUser() {
        User user = new User();
        user.setName("李三");
        user.setUsername("lisan");
        user.setEmail("1051956253@qq.com");
        user.setPassword("123456");
        logger.info("reslut={}",userDao.saveUser(user));
        logger.info("reslut userId={}",user.getId());
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
        User user = userDao.getUserById(1l);
        logger.info(user.toString());
    }



    @Test
    public void listUsersByNameLike() {
        String name = "张";
        List<User> users = userDao.listUsersByNameLike(name ,  1);
        users.stream().forEach(user -> logger.info(user.toString()));


    }

    @Test
    public void listUsersByUsernames() {
    }
}