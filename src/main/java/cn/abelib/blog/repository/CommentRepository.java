package cn.abelib.blog.repository;

import cn.abelib.blog.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by abel on  2017/11/18.
 * 评论的资源库接口
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     *  删除
     * @param userId
     * @param id
     * @return
     */
    Integer deleteByIdEqualsAndUserIdEquals(Long userId, Long id);

    /**
     *  查找博客
     * @param blogId
     * @return
     */
    List<Comment> findByBlogId(Long blogId);
}
