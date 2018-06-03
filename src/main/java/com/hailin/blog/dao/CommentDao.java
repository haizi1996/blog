package com.hailin.blog.dao;

import com.hailin.blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {

    /**
     * 更新评论
     * @param comment
     * @return
     */
    Integer updataComment(Comment comment);


    /**
     * 保存评论
     * @return
     */
    Integer saveComment(Comment comment);

    /**
     * 获取评论
     * @param commentId
     * @param status
     * @return
     */
    Comment findCommentById(@Param("commentId") Long commentId , @Param("commentStatus") Integer status );

    /**
     * 获取评论集合
     * @param blogId
     * @param userId
     * @param status
     * @return
     */

    List<Comment> findComments (@Param("blogId") Long blogId , @Param("userId") Integer userId ,@Param("commentStatus") Integer status );
}
