package com.hailin.model;



//import org.springframework.security.core.GrantedAuthority;

/**
 * 权限.
 * 
 */
public class Authority  {

	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
//	@Override
	public String getAuthority() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
