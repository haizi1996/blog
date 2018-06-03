package com.hailin.blog.dao;

import com.hailin.blog.model.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VoteDao {

    /**
     * 根据id查询Vote实体
     * @param id
     * @return
     */
    Vote findVoteById(@Param("voteId") Long id ,  @Param("voteStatus") Integer voteStatus );

    /**
     * 移除点赞
     * @param vote
     * @return
     */
    Integer updateVote(Vote  vote);

    /**
     * 创建点赞
     * @param blogId
     * @param userId
     * @param vote
     * @return
     */
    Integer saveVote(@Param("blogId") Long blogId, @Param("userId")Integer userId, @Param("vote") Vote vote);
}
