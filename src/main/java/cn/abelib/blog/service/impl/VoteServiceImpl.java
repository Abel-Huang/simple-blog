package cn.abelib.blog.service.impl;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Vote;
import cn.abelib.blog.repository.VoteRepository;
import cn.abelib.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Created by abel on 2017/11/21.
 */
@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteRepository voteRepository;

    /**
     *  点赞或取消点赞
     * @param userId
     * @param blogId
     * @return
     */
    @Transactional
    @Override
    public Response<Vote> addVote(Long userId, Long blogId) {
        Vote vote = voteRepository.findByBlogIdAndUserId(userId, blogId);
        if (vote == null){
            Vote saveVote = new Vote(userId, blogId);
            saveVote.setVote(1);
            saveVote.setCreateTime(new Timestamp(System.currentTimeMillis()));
            Vote returnVote = voteRepository.save(saveVote);
            // todo 修改blog的信息

            return  Response.success(StatusConstant.GENERAL_SUCCESS, returnVote);
        }
        // 已经点赞过了, 点击后会取消点赞
        if (vote.getVote() == 1){
            vote.setVote(0);
            // todo 修改blog的信息
        }
        // 已经取消了点赞, 点击后会继续点赞
        if (vote.getVote() == 0){
            vote.setVote(1);
            // todo 修改blog的信息
        }
        vote.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Vote returnVote = voteRepository.save(vote);
        return  Response.success(StatusConstant.GENERAL_SUCCESS, returnVote);
    }
}
