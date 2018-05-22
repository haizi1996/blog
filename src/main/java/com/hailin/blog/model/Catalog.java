package com.hailin.blog.model;

import com.hailin.blog.constant.CatalogConstant;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Catalog 实体
 * 分类标签
 * 
 */
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // 用户的唯一标识

	private String name;

	private Integer status;

	private String operator;

	private Date createTime;

	private Date operateTime;

	private String operateIp;

	//谁创建了这个分类
	private User user;
 
	protected Catalog() {
	}
	
	public Catalog(User user, String name) {
		this.name = name;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public Catalog setStatusEnum(CatalogConstant.Status statusEnum){
		setStatus(statusEnum.getCode());
		return this;
	}


	public static Catalog buildNormalCatalogById(Integer catalogId , HttpServletRequest request){
		Catalog catalog = new Catalog();
		catalog.setId(catalogId);
		catalog.setStatusEnum(CatalogConstant.Status.NORMAL);
		catalog.setOperateIp(request.getLocalAddr());
		return catalog;
	}
	public static Catalog buildDeleteCatalogById(Integer catalogId , HttpServletRequest request){
		Catalog catalog = new Catalog();
		catalog.setId(catalogId);
		catalog.setStatusEnum(CatalogConstant.Status.DELETE);
		catalog.setOperateIp(request.getLocalAddr());
		return catalog;
	}

}
