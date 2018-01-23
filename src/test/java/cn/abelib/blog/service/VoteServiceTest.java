package cn.abelib.blog.service;

import cn.abelib.blog.bean.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abel on 2017/11/23.
 *  Vote的测试类
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteServiceTest {
    @Autowired
    private VoteService voteService;

    @Test
    public void getVoteByIdTest(){
        Vote vote = voteService.getVoteById(1L);
        System.err.println(vote.getId() + " " + vote.getUser().getId());
    }

    @Test
    public void removeVoteTest(){
        voteService.removeVote(1L);
    }
}
