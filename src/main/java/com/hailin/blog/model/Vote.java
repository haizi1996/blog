package com.hailin.blog.model;

import com.hailin.blog.constant.VoteConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;


/**
 * 点赞实体
 * 
 */
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long blogId;

	private User user;

	private Date createTime;

	private String operator;

	private Date operateTime;

	private String operateIp;

	private Integer status;
	

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public Vote setOperator(String operator) {
		this.operator = operator;
		return this;
	}

	public Long getBlogId() {
		return blogId;
	}

	public Vote setBlogId(Long blogId) {
		this.blogId = blogId;
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

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public static Vote buildNoneVote(Long id , HttpServletRequest request){
		Vote vote = new Vote();
		vote.setId(id);
		vote.setStatus(VoteConstant.Status.DELETED.getCode());
		vote.setOperateIp(request.getLocalAddr());
		return vote;
	}
}
