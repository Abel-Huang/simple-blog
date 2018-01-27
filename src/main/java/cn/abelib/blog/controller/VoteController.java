package cn.abelib.blog.controller;

import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.VoteService;
import cn.abelib.blog.util.ConstraintViolationExceptionHandler;
import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

/**
 * Created by abel on 2017/11/21.
 */
@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private VoteService voteService;

    /**
     *  发表点赞
     * @param blogId
     * @return
     */
    @PostMapping("/add")
    public Response createVote(@RequestParam(value = "blogId") Long blogId){
        Response response;
        Meta meta;
        try {
            blogService.createVote(blogId);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta, HttpConstant.RESPONSE_OK_STR);
        }catch (ConstraintViolationException e){
            meta = new Meta(HttpConstant.REQUEST_ERR, HttpConstant.REQUEST_ERR_STR);
            response = new Response(meta, ConstraintViolationExceptionHandler.getMessage(e));
        }catch (Exception ex){
            meta = new Meta(HttpConstant.SERVER_ERR, ex.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  取消点赞
     * @param id
     * @param blogId
     * @return
     */
    @DeleteMapping("/{id}")
    public Response removeVote(@PathVariable("id") Long id, Long blogId){
        Response response;
        Meta meta;
        try {
            blogService.removeVote(blogId, id);
            voteService.removeVote(id);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }catch (Exception ex){
            meta = new Meta(HttpConstant.SERVER_ERR, ex.getMessage());
            response = new Response(meta, ex.getMessage());
        }
        return response;
    }
}
