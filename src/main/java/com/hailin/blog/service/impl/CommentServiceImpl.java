package com.hailin.blog.service.impl;

import com.hailin.blog.constant.CommentConstant;
import com.hailin.blog.dao.CommentDao;
import com.hailin.blog.model.Comment;
import com.hailin.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public Comment getCommentById(Long commentId , CommentConstant.Status status) {
        return commentDao.findCommentById(commentId, status.getCode());
    }

    @Override
    public List<Comment> getComment(Long blogId, CommentConstant.Status status) {
        return getComment(null,blogId , status);
    }

    @Override
    public List<Comment> getComment(Integer userId, CommentConstant.Status status) {
        return getComment(userId,null , status);
    }

    @Override
    public List<Comment> getComment(Integer userId, Long blogId, CommentConstant.Status status) {
        return commentDao.findComments(blogId ,userId ,status.getCode());
    }

    @Override
    public Integer removeComment(Long commentId) {
        return commentDao.removeComment(commentId);
    }

    @Override
    public Integer updateComment(Comment comment) {
        return commentDao.updataComment(comment);
    }

    @Override
    public Integer saveComment(Comment comment) {
        return commentDao.saveComment(comment);
    }

}
