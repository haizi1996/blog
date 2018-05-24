package com.hailin.blog.service;

import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.constant.SortType;
import com.hailin.blog.model.Blog;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Blog 服务接口.
 * 
 */
public interface BlogService {
	/**
	 * 保存Blog
	 * @param blog
	 * @return
	 */
	Integer saveBlog(Blog blog);
	
	/**
	 * 更新Blog
	 * @param blog
	 * @return
	 */
	Integer updataBlog(Blog blog);
	
	/**
	 * 根据id获取Blog
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);
	
	/**
	 * 根据用户名进行分页模糊查询（最新）
	 * @param user
	 * @return
	 */
	List<Blog> listBlogsByTitleVote(User user, String title);
 
	/**
	 * 根据用户名进行分页模糊查询（最热）
	 * @param user
	 * @return
	 */
	List<Blog> listBlogsByTitleVoteAndSort(User user, String title);

	/**
	 * 根据分类进行查询
	 * @param catalog
	 * @return
	 */
    List<Blog> listBlogsByCatalog(Catalog catalog, int pageIndex, int pageSize);
	/**
	 * 阅读量递增
	 * @param id
	 */
	void readingIncrease(Long id);
	
	/**
	 * 发表评论
	 * @param blogId
	 * @param commentContent
	 * @return
	 */
	Blog createComment(Long blogId, String commentContent);
	
	/**
	 * 删除评论
	 * @param blogId
	 * @param commentId
	 * @return
	 */
	void removeComment(Long blogId, Long commentId);
	
	/**
	 * 点赞
	 * @param blogId
	 * @return
	 */
	Blog createVote(Long blogId);
	
	/**
	 * 取消点赞
	 * @param blogId
	 * @param voteId
	 * @return
	 */
	void removeVote(Long blogId, Long voteId);


	List<Blog> listBlogsByCatalogAndUser(Integer userId , Integer catalogId, CatalogConstant.Status status , SortType sortType , int pageIndex, int pageSize);

	/**
	 * 根据根据条件进行查询
	 * @param userId
	 * @param blogId
	 * @param catalogId
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Blog> listBlogs(Integer userId , Long blogId , Integer catalogId, CatalogConstant.Status status , SortType sortType, int pageIndex, int pageSize);
}
