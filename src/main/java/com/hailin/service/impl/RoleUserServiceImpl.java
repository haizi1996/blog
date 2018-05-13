package com.hailin.service.impl;

import com.hailin.dao.RoleUserDao;
import com.hailin.model.RoleUser;
import com.hailin.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户角色对应的Service
 *
 * @author hailin
 * @date 2018/05/13 09:11
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {


    @Resource
    private RoleUserDao roleUserDao;

    @Override
    public Integer saveUserRole(RoleUser roleUser) {
        return roleUserDao.addUserRole(roleUser);
    }
}
