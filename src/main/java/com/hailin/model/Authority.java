package com.hailin.model;



import org.springframework.security.core.GrantedAuthority;

/**
 * 权限.
 * 
 */
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName; // 用户的唯一标识

	private String name;

	private int status ;//1代表可使用

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Authority() {
	}

	public Authority(String userName, String name) {
		this.userName = userName;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", name='" + name + '\'' +
				", status=" + status +
				'}';
	}
}
