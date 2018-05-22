package com.hailin.blog.service;

import java.util.List;

import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
import org.apache.ibatis.annotations.Param;

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
	void saveAndUpdateCatalog(Catalog catalog);
	


	/**
	 * 根据id获取Catalog
	 * @param id
	 * @param status
	 * @return
	 */
	Catalog getCatalogById(Integer id , CatalogConstant.Status status);
	
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
	/**
	 * 根据重置分类catalog
	 * @param catalog
	 * @return
	 */
	Integer resetCatalog(Catalog catalog);
}
