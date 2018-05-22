package com.hailin.fileServer.service;

import java.util.List;
import java.util.Optional;

import com.hailin.fileServer.domain.File;
import com.hailin.fileServer.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * File 服务.
 * 
 * @since 1.0.0 2017年3月28日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class FileServiceImpl implements FileService {
	
	@Resource
	public FileRepository fileRepository;

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.fileserver.service.FileService#saveFile(com.waylau.spring.boot.fileserver.domain.File)
	 */
	@Override
	public File saveFile(File file) {
		return fileRepository.save(file);
	}

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.fileserver.service.FileService#removeFile(java.lang.Long)
	 */
	@Override
	public void removeFile(String id) {
		fileRepository.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.fileserver.service.FileService#getFileById(java.lang.Long)
	 */
	@Override
	public Optional<File> getFileById(String id) {
		return fileRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.fileserver.service.FileService#listFiles()
	 */
	@Override
	public List<File> listFiles() {
		return fileRepository.findAll();
	}

	@Override
	public void deleteById(String id) {
		 fileRepository.deleteById(id);
	}
}
