package cn.abelib.blog.service;

import cn.abelib.blog.domain.Vote;

/**
 * Created by abel on 2017/11/21.
 */
public interface VoteService {
    /**
     *  根据id查询
     * @param id
     * @return
     */
    Vote getVoteById(Long id);

    /**
     *  取消点赞
     * @param id
     */
    void removeVote(Long id);
}
