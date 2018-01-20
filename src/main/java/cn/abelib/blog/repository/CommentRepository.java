package cn.abelib.blog.repository;

import cn.abelib.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abel on  2017/11/18.
 * 评论的资源库接口
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
