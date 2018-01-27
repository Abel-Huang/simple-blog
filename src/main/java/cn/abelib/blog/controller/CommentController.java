package cn.abelib.blog.controller;

import cn.abelib.blog.bean.Blog;
import cn.abelib.blog.bean.Comment;
import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.CommentService;
import cn.abelib.blog.util.ConstraintViolationExceptionHandler;
import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by abel on 2017/11/21.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    /**
     * 获取Blog的评论列表
     * @param blogId
     * @return
     */
    @GetMapping
    public Response listComments(@RequestParam(value = "blogId")Long blogId){
        Blog blog = blogService.getBlogById(blogId);
        List<Comment>  comments = blog.getComments();
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(comments, meta);
    }

    /**
     *  发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    @GetMapping("/add")
    public Response createComment(Long blogId, String commentContent){
        Response response;
        Meta meta;
        try {
            blogService.createComment(blogId, commentContent);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
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
     *  删除评论
     * @param id
     * @param blogId
     * @return
     */
    @DeleteMapping("/{id}")
    public Response removeComment(@PathVariable("id") Long id, Long blogId){
        Response response;
        Meta meta;
        try {
            blogService.removeComment(blogId, id);
            commentService.removeCommentById(id);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }catch (Exception e){
            meta = new Meta(HttpConstant.SERVER_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }
}
