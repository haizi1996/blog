package com.hailin.service.impl;

import com.github.pagehelper.Page;
import com.hailin.dao.UserDao;
import com.hailin.model.User;
import com.hailin.pagehelper.Pageable;
import com.hailin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public void removeUser(Long id) {

    }

    @Override
    public void removeUsersInBatch(List<User> users) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        return null;
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        return null;
    }
}
