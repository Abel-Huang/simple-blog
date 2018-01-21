package cn.abelib.blog.repository;

import cn.abelib.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abel on 2017/11/18.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
