package com.hailin.blog.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.hailin.blog.constant.BlogConstant;
import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.constant.CountEnum;
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
 * Bloges 服务.
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
    public List<Blog> listBlogsByTitleVote(Integer userId, String title ,Integer pageIndex , Integer pageSize) {
        return listBlogs(userId , null ,  title , null , BlogConstant.Status.NORMAL , SortType.NEW , pageIndex ,pageSize);
    }


    @Override
    public void readingIncrease(Long id) {
        blogDao.countNumberIncrease(id,CountEnum.READ.getCountType());
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        return null;
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {

    }



    @Override
    public void removeVote(Long blogId, Long voteId) {

    }

    @Override
    public List<Blog> listBlogsByTitleVoteAndSort(Integer userId, String keyword , Integer pageIndex , Integer pageSize) {
        return listBlogs( userId, null , keyword , null, BlogConstant.Status.NORMAL , SortType.HOT,  pageIndex,  pageSize);
    }

    @Override
    public List<Blog> listBlogByCatalogAndUser(Integer userId, Integer catalogId, String keyword,
                                                BlogConstant.Status status, SortType sortType , int pageIndex, int pageSize) {
        return listBlogs( userId, null , keyword , catalogId, status, sortType,  pageIndex,  pageSize);
    }

    @Override
    public List<Blog> listBlogs(Integer userId, Long blogId, String keyword , Integer catalogId, BlogConstant.Status status, SortType sortType ,
                                int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex , pageSize);
        return blogDao.findBlogs(blogId , userId , keyword , catalogId , status.getCode() , sortType.getKey());
    }
}