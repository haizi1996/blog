package com.hailin.controller;

import com.hailin.dto.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 安全配置controller
 *
 * @author hailin
 * @date 2018/05/13 15:08
 */
@RestController
public class BrowserSecurityController {


    private static final Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

    /**
     * 请求缓存 ， 存储引发请求跳转的url,也就是之前的缓存，是进入从security之前的缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();


    //请求跳转的工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 当需要身份验证时，跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/required")
    //返回状态码 这是为授权的状态码 401
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requiredAuthentication(HttpServletRequest request , HttpServletResponse response) throws IOException{

        SavedRequest savedRequest = requestCache.getRequest(request ,response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发(从security跳转过来的请求)跳转的请求是:{}" , targetUrl);
            redirectStrategy.sendRedirect(request , response , " ");
        }
        return new SimpleResponse("访问的资源需要认证") ;
    }
}
