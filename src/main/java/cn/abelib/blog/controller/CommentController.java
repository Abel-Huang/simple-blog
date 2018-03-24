package cn.abelib.blog.controller;

import cn.abelib.blog.pojo.Comment;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.service.CommentService;
import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.result.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by abel on 2017/11/21.
 */
@RestController
@RequestMapping("/portal/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取Blog的评论列表
     * @param blogId
     * @return
     */
    @GetMapping("/list")
    public Response<List<Comment>> listComments(Long blogId){
        return commentService.listComment(blogId);
    }

    /**
     *  发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    @GetMapping("/add")
    public Response createComment(HttpSession session, Long blogId, String commentContent){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return commentService.addComment(user.getId(), blogId, commentContent);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  删除评论
     * @param id
     * @param blogId
     * @return
     */
    @DeleteMapping("/{id}")
    public Response removeComment(HttpSession session, @PathVariable("id") Long id, Long blogId){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return  commentService.removeCommentById(user.getId(), blogId, id);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }
}
