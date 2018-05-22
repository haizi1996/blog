package com.hailin.fileServer.repository;

import com.hailin.fileServer.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * File 存储库.
 * 
 */
public interface FileRepository  extends MongoRepository<File, String>{
}
