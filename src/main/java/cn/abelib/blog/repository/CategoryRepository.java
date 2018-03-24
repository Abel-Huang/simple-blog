package cn.abelib.blog.repository;

import cn.abelib.blog.pojo.Category;
import cn.abelib.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by abel on  2017/11/18.
 */
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    /**
     *  根据用户id查询
     * @param userId
     * @return
     */
    List<Category> findByUserId(Long userId);

    /**
     *  根据用户id和分类名称查询
     * @param userId
     * @param content
     * @return
     */
    List<Category> findByUserIdAndContent(Long userId, String content);

    /**
     *  删除
     * @param userId
     * @param id
     * @return
     */
    Integer deleteByIdEqualsAndUserIdEquals(Long userId, Long id);
}
