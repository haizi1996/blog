package com.hailin.service.impl;


import com.hailin.dao.AuthorityDao;
import com.hailin.model.Authority;
import com.hailin.service.AuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    private AuthorityDao authorityDao;

    @Override
    public Authority getAuthorityById(Integer id , int status) {
        return authorityDao.findOneById(id , status);
    }


    @Override
    public Authority getAuthorityByUserName(String username, int status) {
        return authorityDao.findAuthorityByUserName(username,status);
    }
}
