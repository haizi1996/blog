package com.hailin.blog.controller;


import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.dto.Response;
import com.hailin.blog.model.Blog;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
import com.hailin.blog.service.BlogService;
import com.hailin.blog.service.CatalogService;
import com.hailin.blog.service.UserService;
import com.hailin.blog.utils.BindingResultUtil;
import com.hailin.blog.utils.ConstraintViolationExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private UserService userService;

    @Resource
    private CatalogService catalogService;

//    @GetMapping("/list")
//    public String listBlogs(@RequestParam(value="order",required=false,defaultValue="new") String order,
//                            @RequestParam(value="tag",required=false) Long tag) {
//        System.out.print("order:" +order + ";tag:" +tag );
//        return "redirect:/index?order="+order+"&tag="+tag;
//    }
    /**
     * 获取新增博客的界面
     * @param model
     * @return
     */
    @GetMapping("/{username}/edit")
    public ModelAndView createBlog(@PathVariable("username") String username, Model model) {
        User user = (User)userService.loadUserByUsername(username);
        List<Catalog> catalogs = catalogService.listCatalogs(user.getId());

        model.addAttribute("blog", new Blog(null, null, null));
        model.addAttribute("catalogs", catalogs);
        return new ModelAndView("/userspace/blogedit", "blogModel", model);
    }

    /**
     * 获取编辑博客的界面
     * @param model
     * @return
     */
    @GetMapping("/{username}/edit/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView editBlog(@PathVariable("username") String username, @PathVariable("id") Long id, Model model) {
        // 获取用户分类列表
        User user = (User)userService.loadUserByUsername(username);
        List<Catalog> catalogs = catalogService.getCatalogByUsername(username , CatalogConstant.Status.NORMAL);

        model.addAttribute("blog", blogService.getBlogById(id));
        model.addAttribute("catalogs", catalogs);
        return new ModelAndView("userspace/blogedit", "blogModel", model);
    }

    /**
     * 保存博客
     * @param username
     * @param blog
     * @return
     */
    @PostMapping("/{username}/edit")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveBlog(@PathVariable("username") String username,@Valid @RequestBody Blog blog ,BindingResult bindingResult , HttpServletRequest request) {

        if(bindingResult.hasErrors()){
            Map<String ,String > stringMap = BindingResultUtil.handleBindingResult(bindingResult);
//            String json = JsonUtil.objectToJson(stringMap);
            return ResponseEntity.ok().body(Response.errorParamterResponse("",stringMap));
        }
        // 对 Catalog 进行空处理
        if (blog.getCatalog().getId() == null) {
            return ResponseEntity.ok().body(Response.errorResponse("未选择分类"));
        }
        try {

            // 判断是修改还是新增
            if (blog.getId()!=null) {
                blog.setOperator(username).setOperateIp(request.getLocalAddr());
                blogService.saveBlog(blog);
            } else {
                User user = (User)userService.loadUserByUsername(username);
                blog.setUser(user);
                blogService.saveBlog(blog);
            }

        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(Response.errorResponse( ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Response.errorResponse( e.getMessage()));
        }

        String redirectUrl = "/blog/" + username +"/show" + "/" + blog.getId();
        return ResponseEntity.ok().body(Response.successResponse(redirectUrl));
    }

    	/**
    	 * 删除博客
    	 * @param id
    	 * @param username
    	 * @return
    	 */
    	@DeleteMapping("/{username}/delete/{id}")
    	@PreAuthorize("authentication.name.equals(#username)")
    	public ResponseEntity<Response> deleteBlog(@PathVariable("username") String username,@PathVariable("id") Long id ,HttpServletRequest request) {
    		int rows = 0;
    	    try {
    	        Blog blog = Blog.buildDeleteBlog(id , request).setOperator(username);
    			rows = blogService.updataBlog(blog);
    		} catch (Exception e) {
    			return ResponseEntity.ok().body(Response.errorResponse(e.getMessage()));
    		}
            Response response = null;
    	    if (rows > 0){
                String redirectUrl = "/u/" + username + "/blogs";
                response = Response.successResponse(redirectUrl);
            }else{
    	        response = Response.errorResponse("删除博文失败");
            }
    		return ResponseEntity.ok().body(response);
    	}

    /**
     * 获取博客展示界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{username}/show/{id}")
    public String getBlogById(@PathVariable("username") String username,@PathVariable("id") Long id, Model model) {
        User principal = null;
        Blog blog = blogService.getBlogById(id);
        if(blog != null){
            blog.setCommentSize(blog.getComments() != null ?blog.getComments().size() : 0);
        }
        // 判断操作用户是否是博客的所有者
        boolean isBlogOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal !=null && username.equals(principal.getUsername())) {
                isBlogOwner = true;
            }
        }
        //用户是否是博客的所有者，阅读量加一
        if(!isBlogOwner){
            // 每次读取，简单的可以认为阅读量增加1次
            blogService.readingIncrease(id);
        }

//        // 判断操作用户的点赞情况
//        List<Vote> votes = blog.getVotes();
//        Vote currentVote = null; // 当前用户的点赞情况
//
//        if (principal !=null) {
//            for (Vote vote : votes) {
//                vote.getUser().getUsername().equals(principal.getUsername());
//                currentVote = vote;
//                break;
//            }
//        }

        model.addAttribute("isBlogOwner", isBlogOwner);
        model.addAttribute("blogModel",blog);
//        model.addAttribute("currentVote",currentVote);

        return "userspace/blog";
    }

}
