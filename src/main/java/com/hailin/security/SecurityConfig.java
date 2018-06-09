package com.hailin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends  WebSecurityConfigurerAdapter{
    private static final String KEY = "hailin.com";

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private BlogAuthenticationSuccessHandler blogAuthenticationSuccessHandler;
//
//    @Resource
//    private PasswordEncoder passwordEncoder;
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
//    }


    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
//        return authenticationProvider;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login").failureUrl("/login-error") // 自定义登录界面
//                .successHandler(blogAuthenticationSuccessHandler)

       //         .loginProcessingUrl("/login")//告诉security，使用UsernamePasswordAuthenticationFilter来处理请求 默认是/login
                .and()   //表单等来
                .authorizeRequests()  //对下面的配置授权
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index","/register" , "/login").permitAll() // 都可以访问
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()        //任何请求
                .authenticated()
//                .and().rememberMe().key(KEY)
                .and().exceptionHandling().accessDeniedPage("/index") // 启用 remember me;     //需要认证
                .and()
                .csrf().disable() ;//spring Security默认提供csrf跨站伪造url的防护，这里先把它disable掉;
    }
}
