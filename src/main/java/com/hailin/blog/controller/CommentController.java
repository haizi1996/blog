package com.hailin.blog.controller;

import com.google.common.base.Strings;
import com.hailin.blog.constant.CommentConstant;
import com.hailin.blog.dto.Response;
import com.hailin.blog.model.Blog;
import com.hailin.blog.model.Comment;
import com.hailin.blog.model.User;
import com.hailin.blog.service.CommentService;
import com.hailin.blog.utils.BindingResultUtil;
import com.hailin.blog.utils.ConstraintViolationExceptionHandler;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Response> createComment(Long blogId, String commentContent , HttpServletRequest request ) {
        User principal = null;
        if(Strings.isNullOrEmpty(commentContent)){
            return ResponseEntity.ok().body(Response.errorResponse("评论不能为空"));
        }
        // 判断操作用户是否登录
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder
                .getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        if(principal == null){
            return ResponseEntity.ok().body(Response.errorResponse( "请先登录"));
        }
        Comment savecomment = Comment.buildComment(blogId , commentContent ,request).setUser(principal).setOperator(principal.getUsername());
        try {
            int rows = commentService.saveComment(savecomment);
            return ResponseEntity.ok().body(rows > 0 ? Response.successResponse("发表评论成功"):Response.errorResponse("未知原因,发表评论失败"));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(Response.errorResponse( ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Response.errorResponse( e.getMessage()));
        }
    }

    /**
     * 获取评论列表
     *
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping
    public String listComments(@RequestParam(value = "blogId", required = true) Long blogId, Model model) {
        List<Comment> comments = commentService.getComment(blogId, CommentConstant.Status.NORMAL);

        // 判断操作用户是否是评论的所有者
        String commentOwner = "";
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder
                .getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null) {
                commentOwner = principal.getUsername();
            }
        }

        model.addAttribute("commentOwner", commentOwner);
        model.addAttribute("comments", comments);
        return "/userspace/blog :: #mainContainerRepleace";
    }

    /**
     * 删除评论
     *
     * @return
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')") // 指定角色权限才能操作方法
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Long blogId ,HttpServletRequest request) {

        boolean isOwner = false;
        Comment optionalComment = commentService.getCommentById(id,CommentConstant.Status.NORMAL);
        User user = null;

        if (optionalComment != null) {
            user = optionalComment.getUser();
        } else {
            return ResponseEntity.ok().body(Response.errorResponse( "不存在该评论！"));
        }
        User principal = new User();
        // 判断操作用户是否是评论的所有者
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder
                .getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && Objects.equals(user.getUsername() , principal.getUsername())) {
                isOwner = true;
            }
        }

        if (!isOwner) {
            return ResponseEntity.ok().body(Response.errorResponse("没有操作权限"));
        }

        try {
            optionalComment.setStatusEnum(CommentConstant.Status.DELETED).setOperateIp(request.getLocalAddr()).setOperator(principal.getUsername());
            int rows = commentService.updateComment(optionalComment);
            return ResponseEntity.ok().body(rows > 0 ? Response.successResponse( "处理成功") : Response.errorResponse("未知原因操作失败"));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(Response.errorResponse( ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Response.errorResponse(e.getMessage()));
        }

    }
}
