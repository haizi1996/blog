package com.hailin.blog.service.impl;

import com.hailin.blog.constant.CountEnum;
import com.hailin.blog.constant.VoteConstant;
import com.hailin.blog.dao.BlogDao;
import com.hailin.blog.dao.VoteDao;
import com.hailin.blog.model.Vote;
import com.hailin.blog.service.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class VoteServiceImpl implements VoteService {

    @Resource
    private VoteDao voteDao;

    @Resource
    private BlogDao blogDao;

    @Override
    public Vote getVoteById(Long Id, VoteConstant.Status status) {
        return voteDao.findVoteById(Id , status.getCode());
    }

    @Override
    @Transactional
    public Integer deleteVote(Vote vote) {
        Integer result = voteDao.updateVote(vote);
        if(result > 0 ){
            blogDao.countNumberReduce(vote.getBlogId() , CountEnum.VOTE.getCountType());
        }
        return result;
    }

    @Override
    @Transactional
    public Integer createVote(Long blogId, Integer userId, Vote vote) {
        Integer result = voteDao.saveVote(blogId , userId , vote);
        if(result > 0 ){
            blogDao.countNumberIncrease(blogId , CountEnum.VOTE.getCountType());
        }
        return result;
    }
}
