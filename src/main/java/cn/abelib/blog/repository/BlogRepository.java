package cn.abelib.blog.repository;

import cn.abelib.blog.domain.Blog;
import cn.abelib.blog.domain.Category;
import cn.abelib.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abel on 2017/10/28.
 *  Blog的资源库接口
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {
    /**
     *  根据用户名、博客标题查询
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    /**
     *  根据创建时间排序
     * @param title
     * @param user
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLikeOrderByCreateTimeDesc(User user, String title,  Pageable pageable);

    /**
     *  根据博客分类查询
     * @param category
     * @param pageable
     * @return
     */
    Page<Blog> findByCategory(Category category, Pageable pageable);
}
