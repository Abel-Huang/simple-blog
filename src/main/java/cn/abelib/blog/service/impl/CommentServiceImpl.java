package cn.abelib.blog.service.impl;

import cn.abelib.blog.bean.Comment;
import cn.abelib.blog.repository.CommentRepository;
import cn.abelib.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Comment getCommentById(Long id) {
        return commentRepository.findOne(id);
    }

    @Transactional
    @Override
    public void removeCommentById(Long id) {
        commentRepository.delete(id);
    }
}
