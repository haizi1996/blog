package com.hailin.blog.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.dao.CatalogDao;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.service.CatalogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Catalog 服务接口实现.
 * 
 */
@Service
public class CatalogServiceImpl implements CatalogService {
	@Resource
	private CatalogDao catalogDao;

	@Override
	public void saveAndUpdateCatalog(Catalog catalog) {
		Integer num = catalogDao.countCatalogsByUserIdAndName(catalog.getUser().getId() , catalog.getName() , CatalogConstant.Status.NORMAL.getCode() );
		if(num > 0){
			throw new IllegalArgumentException("分类已经存在");
		}
		Integer rows = 0 ;
		if(catalog.getId() == null){
			rows = catalogDao.saveCatalog(catalog);
		}else{
			rows = catalogDao.resetCatalog(catalog);

		}
		Preconditions.checkArgument(rows > 0 , "服务器异常，创建分类失败!");
	}



	@Override
	public Catalog getCatalogById(Integer id , CatalogConstant.Status status) {
		return catalogDao.findByCatalogId(id , status.getCode());
	}

	@Override
	public List<Catalog> listCatalogs(Integer userId ) {
//		PageHelper.startPage(pageIndex, pageSize);
		return catalogDao.findByUserId(userId , CatalogConstant.Status.NORMAL.getCode());
	}

	@Override
	public List<Catalog> getCatalogByUsername(String username ,CatalogConstant.Status catalogStatus) {
		return catalogDao.findByUserName(username , catalogStatus.getCode());
	}

	@Override
	public Integer resetCatalog(Catalog catalog) {
		return catalogDao.resetCatalog(catalog);
	}
}


