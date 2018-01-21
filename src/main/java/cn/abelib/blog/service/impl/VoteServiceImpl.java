package cn.abelib.blog.service.impl;

import cn.abelib.blog.domain.Vote;
import cn.abelib.blog.repository.VoteRepository;
import cn.abelib.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abel on 2017/11/21.
 */
@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findOne(id);
    }

    @Transactional
    @Override
    public void removeVote(Long id) {
        voteRepository.delete(id);
    }
}
