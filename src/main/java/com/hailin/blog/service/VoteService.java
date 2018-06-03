package com.hailin.blog.service;

import com.hailin.blog.constant.VoteConstant;
import com.hailin.blog.model.Vote;

/**
 * 点赞服务接口
 */
public interface VoteService {


    /**
     * 根据id获取Vote实体
     * @param Id
     * @param status
     * @return
     */
    Vote getVoteById(Long Id , VoteConstant.Status status);

    /**
     * 移除评论
     * @param vote
     * @return
     */
    Integer deleteVote(Vote vote);

    /**
     * 点赞
     * @param blogId
     * @param userId
     * @param vote
     * @return
     */
    Integer createVote(Long blogId , Integer userId ,Vote vote);



}
