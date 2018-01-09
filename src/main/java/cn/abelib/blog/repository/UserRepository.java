package cn.abelib.blog.repository;

import cn.abelib.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Created by abel on 2018/1/4.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过name查询(所有类似的)
     * @param name
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     *  通过userName查询(唯一确定的)
     * @param username
     * @return
     */
    User findByUsername(String username);
}
