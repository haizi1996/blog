package com.hailin.blog.service;

import com.hailin.blog.model.Authority;

public interface AuthorityService {

    Authority getAuthorityById(Integer  id , int status);


    Authority getAuthorityByUserName(String  username , int status);

}



