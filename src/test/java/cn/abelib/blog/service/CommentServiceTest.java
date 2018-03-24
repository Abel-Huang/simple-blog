package cn.abelib.blog.service;

import cn.abelib.blog.pojo.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abel on 2017/11/23.
 *  评论的测试类
 *  测试通过
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Test
    public void getCommentByIdTest(){
        Comment comment = commentService.getCommentById(1L);
        System.err.println(comment.getId() + " " + comment.getUser().getId() + " " + comment.getContent());
    }

    @Test
    public void removeCommentTest(){
        commentService.removeCommentById(1L);
    }
}
