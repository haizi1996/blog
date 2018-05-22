package com.hailin.blog.model;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;


/**
 * User 实体
 * 
 */
public class User implements  Serializable,UserDetails {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户的唯一标识
	 */
	private Integer id;

	/**
	 * 姓名
	 */
	@Size(min=2, max=20)
	private String name;

	//邮箱
	@Size(max=50)
	private String email;

	//用户名 用户账号，用户登录时的唯一标识
	@Size(min=3, max=20)
	private String username;

	// 登录时密码
	@Size(max=100)
	private String password;

	// 图片地址
	private String imageUrl;

	//用户状态,1:正常状态 , 0代表冻结状态，2代表删除
	private int status;

	//备注
	private String remark ;
	//用户组
	private Integer userGroupId ;

	//最后操作者  也是user表的userName
	private String  operator ;
	//创建时间
	private Date createTime ;

	//最后操作时间
	private Date operateTime ;

	//最后登录时间
	private Date lastLoginTime ;

	//密码最后修改时间
	private Date lastModifyPasswordTime ;

	//最后操作的IP地址
	private String operateIp;

	//用户的权限 一对多
	private List<Authority> authorities;

	//角色
	private List<Role> roles ;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", status=" + status +
				", remark='" + remark + '\'' +
				", userGroupId=" + userGroupId +
				", operator='" + operator + '\'' +
				", createTime=" + createTime +
				", operateTime=" + operateTime +
				", lastLoginTime=" + lastLoginTime +
				", lastModifyPasswordTime=" + lastModifyPasswordTime +
				", operateIp='" + operateIp + '\'' +
				", authorities=" + authorities +
				", roles=" + roles +
				'}';
	}

	/**
	 * 登录账号是否未过期
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账号是否未冻住  可以恢复
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 登录密码过期，比如30天修改一次
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账号是否可使用 一般是不能恢复的
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastModifyPasswordTime() {
		return lastModifyPasswordTime;
	}

	public void setLastModifyPasswordTime(Date lastModifyPasswordTime) {
		this.lastModifyPasswordTime = lastModifyPasswordTime;
	}

	public String getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public void setEncodePassword(String password) {
		this.password = password;
	}
}
