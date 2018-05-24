package com.hailin.blog.model;

import com.hailin.blog.constant.CommentConstant;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;


/**
 * Comment 实体
 * 
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识

	@Size(min=2, max=500,message = "评论字数限制在2到500之间")
	private String content;
 
	private User user;

	private Blog blog;

	private Date createTime;

	private String operator;

	private Date operateTime;

	private String operateIp;

	private Integer status;
 
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

	public Comment setId(Long id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Comment setContent(String content) {
		this.content = content;
		return this;
	}
	public User getUser() {
		return user;
	}
	public Comment setUser(User user) {
		this.user = user;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public Comment setOperator(String operator) {
		this.operator = operator;
		return this;
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

	public Comment setOperateIp(String operateIp) {
		this.operateIp = operateIp;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Comment setStatusEnum(CommentConstant.Status statusEnum){
		setStatus(statusEnum.getCode());
		return this;
	}

	public Blog getBlog() {
		return blog;
	}

	public Comment setBlog(Blog blog) {
		this.blog = blog;
		return this;
	}


	public static Comment buildComment(Long blogId , String commentContent , HttpServletRequest request){
		Blog blog = new Blog().setId(blogId);
		Comment comment = new Comment().setBlog(blog).setContent(commentContent).setOperateIp(request.getLocalAddr());
		return comment;
	}

}
