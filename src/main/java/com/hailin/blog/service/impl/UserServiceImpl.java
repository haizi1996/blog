package com.hailin.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailin.blog.dao.AuthorityDao;
import com.hailin.blog.dao.UserDao;
import com.hailin.blog.dao.RoleUserDao;
import com.hailin.blog.model.RoleUser;
import com.hailin.blog.model.User;
import com.hailin.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Resource
    private AuthorityDao authorityDao;

    @Resource
    private RoleUserDao roleUserDao;

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {
        User user1 = null;
        Long primarykey = userDao.saveUser(user);
        if(primarykey > 0){
            RoleUser roleUser = RoleUser.createBolgerRole(user.getId());
            roleUserDao.addUserRole(roleUser);
            user1 = userDao.getUserById(user.getId());
        }

        return   Optional.ofNullable(user1);

    }

    @Override
    public Integer removeUser(Integer id) {
        return userDao.removeUser(id);
    }

    @Override
    public Integer removeUsersInBatch(List<Long> ids) {
        return userDao.removeUsersInBatch(ids);
    }

    @Override
    public Integer updateUser(User user) {
        Integer row = userDao.updateUser(user);
        return row;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public PageInfo<User> listUsers( Integer pageNum , Integer pageSize , int status) {
        return listUserAndRolesByNameLike(null , pageNum , pageSize , status);
    }

    @Override
    public PageInfo<User> listUserAndRolesByNameLike(String name, Integer pageNum , Integer pageSize , int status) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userDao.listUserAndRolesByNameLike(name , status);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        return userDao.listUsersByUsernames(usernames);
    }

    @Override
    public User getUserByUserName(String userName , Integer status) {
        return userDao.findByUsername(userName , status);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        logger.info("登录用户名:{}",userName);
        return userDao.findUserDetailsByUsername(userName,null);
    }
}
