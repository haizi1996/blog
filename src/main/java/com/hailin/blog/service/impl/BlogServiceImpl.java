package com.hailin.blog.service.impl;

import java.util.List;
import java.util.Optional;

import com.hailin.blog.dao.BlogDao;
import com.hailin.blog.model.Blog;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
import com.hailin.blog.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Blog 服务.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        boolean isNew = (blog.getId() == null);
        return null;
    }

    @Transactional
    @Override
    public void removeBlog(Long id) {

    }

    @Override
    public Blog getBlogById(Long id) {
        return null;
    }

    @Override
    public List<Blog> listBlogsByTitleVote(User user, String title) {
        return null;
    }


    @Override
    public void readingIncrease(Long id) {

    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        return null;
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {

    }

    @Override
    public Blog createVote(Long blogId) {
        return null;
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {

    }

    @Override
    public List<Blog> listBlogsByTitleVoteAndSort(User user, String title) {
        return null;
    }

    @Override
    public List<Blog> listBlogsByCatalog(Catalog catalog) {
        return null;
    }
}