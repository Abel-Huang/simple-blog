package cn.abelib.blog.service.impl;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.exception.GlobalException;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Comment;
import cn.abelib.blog.repository.CommentRepository;
import cn.abelib.blog.service.CommentService;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by abel on 2017/11/21.
 *  CommentService接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Override
    public Response<Comment> getCommentById(Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null){
            throw new GlobalException(StatusConstant.COMMENT_NOT_EXISTS);
        }
        return  Response.success(StatusConstant.GENERAL_SUCCESS, comment);
    }

    @Transactional
    @Override
    public Response removeCommentById(Long userId, Long blogId, Long id) {
        Integer resultCount =  commentRepository.deleteByIdEqualsAndUserIdEquals(userId, id);
        // todo 修改blog的信息

        if (resultCount != 1){
            throw new GlobalException(StatusConstant.COMMENT_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    @Transactional
    @Override
    public Response<Comment> addComment(Long userId, Long blogId, String content){
        if (StringUtils.isNotBlank(content)){
            throw new GlobalException(StatusConstant.PRAM_BIND_ERROR);
        }
        Comment comment = new Comment(userId, blogId, content);
        comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        // todo 修改blog的信息

        Comment saveComment = commentRepository.save(comment);
        if (saveComment == null){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, saveComment);
    }

    @Override
    public Response<List<Comment>> listComment(Long blogId) {
        List<Comment> comments = Lists.newArrayList();
        comments = commentRepository.findByBlogId(blogId);
        if (comments == null || CollectionUtils.isEmpty(comments)){
            throw new GlobalException(StatusConstant.CATEGORY_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, comments);
    }
}
