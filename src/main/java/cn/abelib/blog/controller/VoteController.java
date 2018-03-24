package cn.abelib.blog.controller;

import cn.abelib.blog.pojo.User;
import cn.abelib.blog.pojo.Vote;
import cn.abelib.blog.service.VoteService;
import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.result.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by abel on 2017/11/21.
 */
@RestController
@RequestMapping("/portal/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    /**
     *  点赞
     * @param blogId
     * @return
     */
    @PostMapping("/vote")
    public Response<Vote> vote(HttpSession session, Long blogId){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return   voteService.addVote(user.getId(), blogId);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }
}
