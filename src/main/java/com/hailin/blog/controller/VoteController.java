package com.hailin.blog.controller;

import com.hailin.blog.constant.VoteConstant;
import com.hailin.blog.dto.Response;
import com.hailin.blog.model.User;
import com.hailin.blog.model.Vote;
import com.hailin.blog.service.BlogService;
import com.hailin.blog.service.VoteService;
import com.hailin.blog.utils.ConstraintViolationExceptionHandler;
import com.hailin.blog.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@Controller
@RequestMapping("/votes")
public class VoteController {

    @Resource
    private BlogService blogService;

    @Resource
    private VoteService voteService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 发表点赞
     * @param blogId
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
    public ResponseEntity<Response> createVote(Long blogId , HttpServletRequest request) {
        try {
            User user = SecurityUtil.getSecurityUser();
            Vote vote = Vote.buildNoneVote(blogId , request).setOperator(user.getUsername());
            voteService.createVote(blogId, user.getId() , vote);
        } catch (ConstraintViolationException e)  {
            logger.error(e.getMessage() , e);
            return ResponseEntity.ok().body(Response.errorResponse( ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return ResponseEntity.ok().body(Response.errorResponse( e.getMessage()));
        }
        return ResponseEntity.ok().body(Response.successResponse("点赞成功"));
    }

    /**
     * 删除点赞
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Long blogId , HttpServletRequest request) {

        boolean isOwner = false;
        Vote vote = voteService.getVoteById(id , VoteConstant.Status.NORMAL);
        // 判断操作用户是否是点赞的所有者
        User currentUser = SecurityUtil.getSecurityUser();
        if (currentUser !=null && vote != null && vote.getUser() != null  && currentUser.getUsername().equals(vote.getUser().getUsername())) {
            isOwner = true;
        }
        if (!isOwner) {
            return ResponseEntity.ok().body(Response.errorResponse("没有操作权限"));
        }
        try {
            Vote deleteVote = Vote.buildNoneVote(id, request).setOperator(currentUser.getName()).setBlogId(vote.getBlogId());
            voteService.deleteVote(deleteVote);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(Response.errorResponse(ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Response.errorResponse( e.getMessage()));
        }
        return ResponseEntity.ok().body(Response.successResponse( "取消点赞成功"));
    }
}
