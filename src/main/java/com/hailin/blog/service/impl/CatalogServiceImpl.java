package com.hailin.blog.service.impl;


import com.github.pagehelper.PageHelper;
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
	public Catalog saveCatalog(Catalog catalog) {
		Integer num = catalogDao.countCatalogsByUserIdAndName(catalog.getUser().getId() , catalog.getName() );
	if(num > 0){
		throw new IllegalArgumentException("分类已经存在");
	}
		Integer rows = catalogDao.saveCatalog(catalog);
		return rows > 0 ? catalogDao.findByCatalogId(catalog.getId()) : null;
	}

	@Override
	public Integer removeCatalog(Integer id) {
		return catalogDao.removeCatalog(id);
	}

	@Override
	public Catalog getCatalogById(Integer id) {
		return catalogDao.findByCatalogId(id);
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
}


