package com.hailin.blog.controller;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hailin.blog.constant.BlogConstant;
import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.constant.SortType;
import com.hailin.blog.dto.Response;
import com.hailin.blog.model.Blog;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
import com.hailin.blog.model.Vote;
import com.hailin.blog.service.BlogService;
import com.hailin.blog.service.CatalogService;
import com.hailin.blog.service.UserService;
import com.hailin.blog.utils.ConstraintViolationExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * 用户主页空间控制器.
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {

	@Resource
	private UserService userService;
	
	@Resource
	private BlogService blogService;

	@Resource
	private CatalogService catalogService;
	

	
	@GetMapping("/{username}")
	public String userSpace(@PathVariable("username") String username, Model model) {
//		User  user = (User)userService.loadUserByUsername(username);
//		model.addAttribute("user", user);
		return "redirect:/u/" + username + "/blogs";
	}


	/**
	 * 获取个人设置的页面
	 * @param username
	 * @param model
	 * @return
	 */
	@GetMapping("/{username}/profile")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView profile(@PathVariable("username") String username, Model model) {
		User user = (User)userService.loadUserByUsername(username);
		Response response ;
		if(user != null){
			response = Response.successResponse(user);
		}else{
			response = Response.errorResponse("获取失败!");
		}
		model.addAttribute("response", response);
		return new ModelAndView("userspace/profile", "userModel", model);
	}

	/**
	 * 保存个人设置
	 * @param user
	 * @return
	 */
	@PostMapping("/{username}/profile")
	@PreAuthorize("authentication.name.equals(#username)")
	public String saveProfile(@PathVariable("username") String username,User user) {
		User originalUser = userService.getUserById(user.getId());
		originalUser.setEmail(user.getEmail());
		originalUser.setName(user.getName());
		originalUser.setRemark(user.getRemark());
		// 判断密码是否做了变更
		String rawPassword = originalUser.getPassword();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePasswd = encoder.encode(user.getPassword());
		boolean isMatch = encoder.matches(rawPassword, encodePasswd);
		if (!isMatch) {
			originalUser.setEncodePassword(user.getPassword());
		}else {
			originalUser.setEncodePassword(null);
		}

		userService.updateUser(originalUser);
		return "redirect:/u/" + username + "/profile";
	}

	/**
	 * 获取编辑头像的界面
	 * @param username
	 * @param model
	 * @return
	 */
	@GetMapping("/{username}/avatar")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView avatar(@PathVariable("username") String username, Model model) {
		User  user = (User)userService.loadUserByUsername(username);
		model.addAttribute("user", user);
		return new ModelAndView("/userspace/avatar", "userModel", model);
	}


	/**
	 * 保存头像
	 * @param username
	 * @return
	 */
	@PostMapping("/{username}/avatar")
	@PreAuthorize("authentication.name.equals(#username)")
	public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user) {
		String imageUrl = user.getImageUrl();
		User originalUser = userService.getUserById(user.getId());
		originalUser.setImageUrl(imageUrl);
		userService.updateUser(originalUser);
		return ResponseEntity.ok().body(Response.successResponse(imageUrl).setMessage( "处理成功"));
	}


	@GetMapping("/{username}/blogs")
	public String listBlogsByOrder(@PathVariable("username") String username,
			@RequestParam(value="order",required=false,defaultValue="new") String order,
			@RequestParam(value="catalog",required=false ) Integer catalogId,
			@RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
			@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			Model model) {

		User  user = (User)userService.loadUserByUsername(username);
		List<Blog> blogs = Lists.newArrayList();
		// 分类查询
		if (catalogId != null && catalogId > 0) {
			 blogs = blogService.listBlogByCatalogAndUser(user.getId() , catalogId, keyword , BlogConstant.Status.NORMAL ,
					 SortType.parseToSortType(order), pageIndex ,pageSize);
			order = "";
			// 最热查询
		} else if (SortType.HOT.getKey().equals(order)) {
			blogs = blogService.listBlogsByTitleVoteAndSort(user.getId()  , keyword  , pageIndex ,pageSize);
			// 最新查询
		} else if (SortType.NEW.getKey().equals(order)) {
			blogs = blogService.listBlogsByTitleVote(user.getId() , keyword, pageIndex, pageSize);
		}

		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("catalogId", catalogId);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", PageInfo.of(blogs));
		model.addAttribute("blogList", blogs);
		return (async==true?"userspace/u :: #mainContainerRepleace":"userspace/u");
	}

}
