package com.hailin.blog.dao;

import com.hailin.blog.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogDao {

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Integer updateBlog(Blog blog);

    /**
     * 根据catalogId分页查询blog
     * @param catalogId
     * @return
     */
    List<Blog> findByCatalogId(@Param("catalogId") Integer catalogId);

    /**
     * 根据catalogId分页查询blog
     * @param blogId
     * @return
     */
    Blog findBlogById(@Param("blogId") Long blogId , @Param("status") Integer status);

    /**
     * 保存blog
     * @param blog
     * @return
     */
    Integer saveBlog(Blog blog);

    /**
     * 根据条件查询博客列表
     * @param blogId
     * @param userId
     * @param catalogId
     * @param status
     * @return
     */
    List<Blog> findBlogs(@Param("blogId") Long blogId ,  @Param("userId") Integer userId , @Param("catalogId") Integer catalogId ,@Param("status") Integer status , @Param("sortType") String sortType);
}
