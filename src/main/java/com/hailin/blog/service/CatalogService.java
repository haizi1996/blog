package com.hailin.blog.service;

import java.util.List;

import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;

/**
 * Catalog 服务接口.
 * 
 */
public interface CatalogService {
	/**
	 * 保存Catalog
	 * @param catalog
	 * @return
	 */
	Catalog saveCatalog(Catalog catalog);
	
	/**
	 * 删除Catalog
	 * @param id
	 * @return
	 */
	Integer removeCatalog(Integer id);

	/**
	 * 根据id获取Catalog
	 * @param id
	 * @return
	 */
	Catalog getCatalogById(Integer id);
	
	/**
	 * 根据userId获取Catalog列表
	 * @return
	 */
	List<Catalog> listCatalogs(Integer userId );

	/**
	 * 根据username获取Catalog列表
	 * @param username
	 * @return
	 */
	List<Catalog> getCatalogByUsername(String username , CatalogConstant.Status catalogStatus);
}
