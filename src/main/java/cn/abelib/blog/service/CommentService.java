package cn.abelib.blog.service;

import cn.abelib.blog.domain.Comment;

/**
 * Created by abel on 2017/11/21.
 */
public interface CommentService {
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     *  删除评论
     * @param id
     */
    void removeCommentById(Long id);
}
