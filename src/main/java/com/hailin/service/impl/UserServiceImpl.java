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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public Optional<User> saveUser(User user) {

        Long primarykey = userDao.saveUser(user);
        return   Optional.ofNullable(primarykey > 0 ? userDao.getUserById(primarykey):null);

    }

    @Override
    public Integer removeUser(Long id) {
        return userDao.removeUser(id);
    }

    @Override
    public Integer removeUsersInBatch(List<Long> ids) {
        return userDao.removeUsersInBatch(ids);
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
