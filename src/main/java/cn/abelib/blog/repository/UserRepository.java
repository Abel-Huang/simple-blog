package cn.abelib.blog.repository;

import cn.abelib.blog.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by abel on 2017/11/4.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *  计算当前UserName是否已经被使用过
     * @param username
     * @return
     */
    Integer countByUsername(String username);

    /**
     *  通过用户名检测用户的登录状态
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     *
     * @param id
     * @param password
     * @return
     */
    Integer countUserByIdAndPassword(Long id, String password);
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

    /**
     *  根据用户名称列表查询用户列表
     * @param username
     * @return
     */
    List<User> findByUsernameIn(List<String> username);
}
