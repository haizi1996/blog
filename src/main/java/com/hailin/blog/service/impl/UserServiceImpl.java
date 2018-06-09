package com.hailin.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hailin.blog.dao.AuthorityDao;
import com.hailin.blog.dao.UserDao;
import com.hailin.blog.dao.RoleUserDao;
import com.hailin.blog.enumPackage.RoleEnum;
import com.hailin.blog.model.RoleUser;
import com.hailin.blog.model.User;
import com.hailin.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Optional<User> saveUser(User user , RoleEnum roleEnum) {
        User user1 = null;
        Long primarykey = userDao.saveUser(user);
        if(primarykey > 0){
            RoleUser roleUser = RoleUser.createRoleUser(user.getId() , roleEnum);
            roleUser.setOperator(user.getOperator());
            roleUser.setOperateIp(user.getOperateIp());
            roleUserDao.addUserRole(roleUser);
            user1 = userDao.getUserById(user.getId());
        }
        return   Optional.ofNullable(user1);
    }

    @Override
    public Integer removeUser(Integer id , String username) {
        return userDao.removeUser(id , username);
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
    public PageInfo<User> listUsers( Integer pageNum , RoleEnum roleEnum, Integer pageSize , Integer status) {
        return listUserAndRolesByNameLike(null , roleEnum, pageNum , pageSize , status);
    }

    @Override
    public PageInfo<User> listUserAndRolesByNameLike(String name, RoleEnum roleEnum, Integer pageNum , Integer pageSize , int status) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userDao.listUserAndRolesByNameLike(name ,roleEnum== null ? null : roleEnum.getRoleId() , status);
        if(CollectionUtils.isEmpty(users)){
            users = Lists.newArrayList();
        }
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        if(CollectionUtils.isEmpty(usernames)){
            return new ArrayList<>();
        }
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
