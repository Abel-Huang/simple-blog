package cn.abelib.blog.service;

import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Comment;

import java.util.List;

/**
 * Created by abel on 2017/11/21.
 */
public interface CommentService {
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Response<Comment> getCommentById(Long id);

    /**
     *  删除评论
     * @param id
     * @param userId
     */
    Response removeCommentById(Long userId, Long blogId, Long id);

    /**
     *  添加评论
     * @param userId
     * @param blogId
     * @param content
     * @return
     */
    Response<Comment> addComment(Long userId, Long blogId, String content);

    /**
     *  列出所有的评论
     * @param blogId
     * @return
     */
    Response<List<Comment>> listComment(Long blogId);
}
