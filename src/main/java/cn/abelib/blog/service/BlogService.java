package cn.abelib.blog.service;

import cn.abelib.blog.bean.Blog;
import cn.abelib.blog.bean.Category;
import cn.abelib.blog.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by abel on 2017/11/11.
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

    /**
     *  创建评论
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     *  删除评论
     * @param blogId
     * @param commentId
     */
    void removeComment(Long blogId, Long commentId);

    /**
     *  创建点赞
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     *  取消点赞
     * @param blogId
     * @param voteId
     */
    void removeVote(Long blogId, Long voteId);

    /**
     *  通过分类进行查找
     * @param category
     * @param pageable
     * @return
     */
    Page<Blog> listBlogByCategory(Category category, Pageable pageable);
}
