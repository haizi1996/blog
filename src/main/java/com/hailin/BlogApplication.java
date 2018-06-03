package com.hailin;

import com.github.pagehelper.PageInfo;
import com.hailin.blog.model.User;
import com.hailin.blog.service.UserService;
import com.hailin.blog.trie.WordTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {


	private static final Integer PAGE_SIZE = 3;

	@Resource
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//允许上传的文件最大值
		factory.setMaxFileSize("50MB");
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("50MB");
		return factory.createMultipartConfig();
	}

	@Override
	public void run(String... args) throws Exception {
		initWordTree();
	}


	private void initWordTree(){
		boolean hasNext = true;
		Integer pageIndex = 1 ;
		PageInfo<User> userPageInfo = null;
		WordTree<Integer> wordTree = WordTree.getWordTree();
		do{
			userPageInfo = userService.listUsers(pageIndex ++ ,  PAGE_SIZE , 1);
			userPageInfo.getList().stream().forEach(user -> wordTree.addNode(user.getUsername() , user.getId()));
		}while (userPageInfo != null && userPageInfo.isHasNextPage());
	}
}
