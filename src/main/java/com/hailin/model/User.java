package com.hailin.model;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Size;


/**
 * User 实体
 * 
 */
public class User implements  Serializable,UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识

	@Size(min=2, max=20)
	private String name;

	@Size(max=50)
	private String email;

	@Size(min=3, max=20)
	private String username; // 用户账号，用户登录时的唯一标识

	@Size(max=100)
	private String password; // 登录时密码

	private String imageUrl; // 头像图片地址

	private int status;

	private List<Authority> authorities;//用户的权限 一对多

	public User() {
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Lists.transform(!CollectionUtils.isEmpty(this.authorities) ? this.authorities : Lists.newArrayList() ,authority -> new SimpleGrantedAuthority(authority.getAuthority()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public User(String name, String email, String username, String password) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	@Override
	public String toString() {
		return String.format("User[id=%d, username='%s', name='%s', email='%s', password='%s' , imageUrl='%s']", id, username, name, email,
				password,imageUrl);
	}

	/**
	 * 账号是否未过期
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账号是否未冻住
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 验证账号是否可用
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账号是否可使用
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}


}
