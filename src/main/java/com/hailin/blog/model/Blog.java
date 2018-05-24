package com.hailin.blog.model;

import com.github.rjeschke.txtmark.Processor;
import com.hailin.blog.constant.BlogConstant;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * Blog 实体
 * 
 */
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // 用户的唯一标识

	@Size(min=2, max=50,message = "标题长度在2和50之间")
	@NotNull(message = "标题不能为空")
	private String title;
	
	@Size(min=2, max=300 ,message = "摘要长度在2和300之间")
	@NotNull(message = "摘要不能为空")
	private String summary;


	@Length(min = 1,message = "标签不能为空")
	private String tags;  // 标签


	private User user;

	private Date createTime;

	private Integer readSize = 0; // 访问量、阅读量

	private Integer commentSize = 0;  // 评论量

	private Integer voteSize = 0;  // 点赞量


	private List<Comment> comments;

	private List<Vote> votes;

	@NotNull(message = "未选择分类")
	private Catalog catalog;

	private String operator;

	private Date operateTime;

	private String operateIp;

	private Integer status;


	@Size(min=2,message = "博客主体长度不能小于2为空")
	@NotNull(message = "博客主体不能为空")
	private String content;
	@Size(min=2)
	private String htmlContent; // 将 md 转为 html

	public String getOperator() {
		return operator;
	}

	public Blog setOperator(String operator) {
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

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	protected Blog() {
		// TODO Auto-generated constructor stub
	}
	public Blog(String title, String summary,String content) {
		this.title = title;
		this.summary = summary;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}

	public Blog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.htmlContent = Processor.process(content);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHtmlContent() {
		return htmlContent;
	}
	public Integer getReadSize() {
		return readSize;
	}
	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}
	public Integer getCommentSize() {
		return commentSize;
	}
	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}
	public Integer getVoteSize() {
		return voteSize;
	}
	public void setVoteSize(Integer voteSize) {
		this.voteSize = voteSize;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
		this.commentSize = this.comments.size();
	}
 
	/**
	 * 添加评论
	 * @param comment
	 */
	public void addComment(Comment comment) {
		this.comments.add(comment);
		this.commentSize = this.comments.size();
	}
	/**
	 * 删除评论
	 * @param commentId
	 */
	public void removeComment(Long commentId) {
		for (int index=0; index < this.comments.size(); index ++ ) {
			if (comments.get(index).getId() == commentId) {
				this.comments.remove(index);
				break;
			}
		}
		
		this.commentSize = this.comments.size();
	}
 
	/**
	 * 点赞
	 * @param vote
	 * @return
	 */
	public boolean addVote(Vote vote) {
		boolean isExist = false;
		// 判断重复
		for (int index=0; index < this.votes.size(); index ++ ) {
			if (this.votes.get(index).getUser().getId() == vote.getUser().getId()) {
				isExist = true;
				break;
			}
		}
		
		if (!isExist) {
			this.votes.add(vote);
			this.voteSize = this.votes.size();
		}

		return isExist;
	}
	/**
	 * 取消点赞
	 * @param voteId
	 */
	public void removeVote(Long voteId) {
		for (int index=0; index < this.votes.size(); index ++ ) {
			if (this.votes.get(index).getId() == voteId) {
				this.votes.remove(index);
				break;
			}
		}
		
		this.voteSize = this.votes.size();
	}
	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
		this.voteSize = this.votes.size();
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public static Blog buildDeleteBlog(Long id ,HttpServletRequest request){
		Blog blog = new Blog();
		blog.setId(id);
		blog.setStatus(BlogConstant.Status.DELETE.getCode());
		blog.setOperateIp(request.getLocalAddr());
		return blog;
	}

}
