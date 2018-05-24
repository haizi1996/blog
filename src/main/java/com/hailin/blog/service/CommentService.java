package com.hailin.blog.service;

import com.hailin.blog.constant.CommentConstant;
import com.hailin.blog.model.Blog;
import com.hailin.blog.model.Comment;

import java.util.List;

/**
 * Comment 服务接口.
 * 
 */
public interface CommentService {
	/**
	 * 根据id获取 Comment
	 * @param id
	 * @return
	 */
	Comment getCommentById(Long id , CommentConstant.Status status);


	/**
	 * 获取 Comment集合
	 * @return
	 */
	List<Comment> getComment(Long blogId , CommentConstant.Status status);

	/**
	 * 获取 Comment集合
	 * @return
	 */
	List<Comment> getComment(Integer userId  , CommentConstant.Status status);
	/**
	 * 获取 Comment集合
	 * @return
	 */
	List<Comment> getComment(Integer userId , Long blogId , CommentConstant.Status status);


	/**
	 * 删除评论
	 * @param commentId
	 * @return
	 */
	Integer removeComment(Long commentId);

	/**
	 * 更新评论
	 * @param comment
	 * @return
	 */
	Integer updateComment(Comment comment);

	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	Integer saveComment(Comment comment);
}
