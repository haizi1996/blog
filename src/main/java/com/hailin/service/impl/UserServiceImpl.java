package com.hailin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailin.dao.AuthorityDao;
import com.hailin.dao.UserDao;
import com.hailin.model.Authority;
import com.hailin.model.User;
import com.hailin.service.UserService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private AuthorityDao authorityDao;

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {

        User user1 = null;
        Long primarykey = userDao.saveUser(user);
        if(primarykey > 0){
//            authorityDao.addAuthority(new Authority(user.getUsername() , a))
        }

        return   Optional.ofNullable(user1);

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
        Long row = userDao.saveUser(user);
        return row > 0 ? userDao.getUserById(user.getId()) : null;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public PageInfo<User> listUsers( Integer pageNum , Integer pageSize , int status) {
        return listUsersByNameLike(null , pageNum , pageSize , status);
    }

    @Override
    public PageInfo<User> listUsersByNameLike(String name, Integer pageNum , Integer pageSize , int status) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userDao.listUsersByNameLike(name , status);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        return userDao.listUsersByUsernames(usernames);
    }

    @Override
    public User getUserByUserName(String userName , int status) {
        return userDao.findByUsername(userName , status);
    }

    //    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userDao.findByUsername(s);
//    }
}
