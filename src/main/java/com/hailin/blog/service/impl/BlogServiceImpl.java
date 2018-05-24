package com.hailin.blog.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.constant.SortType;
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
    public Integer saveBlog(Blog blog) {
        return blogDao.saveBlog(blog);
    }

    @Transactional
    @Override
    public Integer updataBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        //TODO
        return blogDao.findBlogById(id , 1);
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
    public List<Blog> listBlogsByCatalog(Catalog catalog,int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public List<Blog> listBlogsByCatalogAndUser(Integer userId, Integer catalogId,
            CatalogConstant.Status status, SortType sortType , int pageIndex, int pageSize) {
        return listBlogs( userId, null , catalogId, status, sortType,  pageIndex,  pageSize);
    }

    @Override
    public List<Blog> listBlogs(Integer userId, Long blogId, Integer catalogId, CatalogConstant.Status status, SortType sortType ,
            int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex , pageSize);
        return blogDao.findBlogs(blogId , userId , catalogId , status.getCode() , sortType.getKey());
    }
}