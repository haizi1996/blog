package com.hailin.blog.service.impl;


import com.hailin.blog.dao.CatalogDao;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
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
		return null;
	}

	@Override
	public void removeCatalog(Long id) {

	}

	@Override
	public Catalog getCatalogById(Long id) {
		return null;
	}

	@Override
	public List<Catalog> listCatalogs(User user) {
		return null;
	}
}


