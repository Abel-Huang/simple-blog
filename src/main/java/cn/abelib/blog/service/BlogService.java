package cn.abelib.blog.service;

import cn.abelib.blog.domain.Blog;
import cn.abelib.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by abel on 2018/1/11.
 */
public interface BlogService {
    /**
     *  保存Blog
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     *  删除Blog
     * @param id
     */
    void removeBlog(Long id);

    /**
     *  更新Blog
     * @param blog
     * @return
     */
    Blog updateBlog(Blog blog);
    /**
     *  通过id查询
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogByTitleLike(User user, String title, Pageable pageable);

    /**
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogByTitleLikeAndSort(User user, String title, Pageable pageable);

    /**
     *  用户阅读量增加
     * @param id
     */
    void readingIncrease(Long id);

}
