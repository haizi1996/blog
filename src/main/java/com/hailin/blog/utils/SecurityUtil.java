package com.hailin.blog.utils;

import com.hailin.blog.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 权限管理的工具类
 */
public class SecurityUtil {

    public static User getSecurityUser(){
        User user = null;
        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

}
