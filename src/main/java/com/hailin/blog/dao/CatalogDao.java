package com.hailin.blog.dao;

import com.hailin.blog.model.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CatalogDao {

    /**
     * 保存分类
     * @param catalog
     * @return
     */
    Integer saveCatalog(Catalog catalog);

    /**
     * 根据分类Id查询分类
     * @param catalogId
     * @return
     */

    Catalog findByCatalogId(@Param("catalogId") Integer catalogId);

    /**
     * 根据userId和分类name进行统计
     * @param userId
     * @param name
     * @return
     */
    Integer countCatalogsByUserIdAndName(@Param("userId") Integer userId, @Param("name") String name);

    /**
     * 删除分类
     * @param catalogId
     * @return
     */
    Integer removeCatalog(@Param("catalogId") Integer catalogId);

    /**
     * 根据用户Id查找分类
     * @param userId
     * @return
     */
    List<Catalog> findByUserId(@Param("userId") Integer userId , @Param("catalogStatus") Integer catalogStatus);

    /**
     * 根据username查找分类
     * @param userName
     * @return
     */
    List<Catalog> findByUserName(@Param("userName") String userName ,@Param("catalogStatus") Integer catalogStatus);

    /**
     * 根据username,catalog名字查找分类
     * @param userName
     * @param name
     * @return
     */
    List<Catalog> findByUserNameAndCatalogName(@Param("userName") String userName , @Param("name") String name);

    /**
     * 根据userId,catalog名字查找分类
     * @param userId
     * @return
     */
    List<Catalog> findByUserIdAndCatalogName(@Param("userId") Integer userId, @Param("name") String name);

}
