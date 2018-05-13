package com.hailin.service;

import com.hailin.model.Authority;

public interface AuthorityService {

    Authority getAuthorityById(Integer  id , int status);



    Authority getAuthorityByUserName(String  username , int status);

}



