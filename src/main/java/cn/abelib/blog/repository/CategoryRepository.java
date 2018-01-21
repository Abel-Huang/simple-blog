package cn.abelib.blog.repository;

import cn.abelib.blog.domain.Category;
import cn.abelib.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by abel on  2017/11/18.
 */
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    /**
     *  根据用户查询
     * @param user
     * @return
     */
    List<Category> findByUser(User user);

    /**
     *  根据用户和分类名称查询
     * @param user
     * @param name
     * @return
     */
    List<Category> findByUserAndName(User user, String name);
}
