package com.hailin.blog.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Size;


/**
 * Comment 实体
 * 
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识

	@Size(min=2, max=500)
	private String content;
 
	private User user;
	
	private Timestamp createTime;
 
	protected Comment() {
		// TODO Auto-generated constructor stub
	}
	public Comment(User user, String content) {
		this.content = content;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
