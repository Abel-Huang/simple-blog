package cn.abelib.blog.repository;

import cn.abelib.blog.pojo.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abel on 2017/11/18.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
    /**
     *  通过博客Id和用户Id查询
     * @return
     */
    Vote findByBlogIdAndUserId(Long blogId, Long userId);
}
