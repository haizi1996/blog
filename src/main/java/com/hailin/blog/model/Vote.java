package com.hailin.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * Like 实体
 * 
 */
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识
 
	private User user;
	
	private Timestamp createTime;
 
	protected Vote() {
	}
	
	public Vote(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
	public Timestamp getCreateTime() {
		return createTime;
	}
 
}
