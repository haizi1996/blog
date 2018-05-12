package com.hailin.service;

import com.hailin.model.Authority;

public interface AuthorityService {

    Authority getAuthorityById(Long  id , int status);



    Authority getAuthorityByUserName(String  username , int status);

}



