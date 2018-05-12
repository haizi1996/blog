package com.hailin.model;

import java.io.Serializable;


/**
 * Catalog 实体
 * 
 */
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识

	private String name;
 
	private User user;
 
	protected Catalog() {
	}
	
	public Catalog(User user, String name) {
		this.name = name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
 
}
