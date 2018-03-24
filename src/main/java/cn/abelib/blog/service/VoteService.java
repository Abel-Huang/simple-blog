package cn.abelib.blog.service;

import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Vote;

/**
 * Created by abel on 2017/11/21.
 */
public interface VoteService {
    /**
     *  点赞或取消点赞
     * @param userId
     * @param blogId
     * @return
     */
    Response<Vote> addVote(Long userId, Long blogId);
}
