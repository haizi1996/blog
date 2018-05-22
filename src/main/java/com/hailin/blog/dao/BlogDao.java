package com.hailin.blog.dao;

import com.hailin.blog.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogDao {

    /**
     * 根据catalogId分页查询blog
     * @param catalogId
     * @return
     */
    List<Blog> findByCatalogId(@Param("catalogId") Integer catalogId);
}
