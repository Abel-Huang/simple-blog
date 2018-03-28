package cn.abelib.blog.service;

import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Blog;
import cn.abelib.blog.pojo.Category;
import cn.abelib.blog.pojo.EsBlog;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.vo.BlogVo;
import cn.abelib.blog.vo.SimpleBlogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by abel on 2017/11/11.
 */
public interface BlogService {
    /**
     *  保存Blog
     * @param blogVo
     * @return
     * @return
     */
    Response<Blog> saveBlog(BlogVo blogVo);

    /**
     *  删除Blog
     * @param userId
     * @param blogId
     */
    Response removeBlog(Long userId, Long blogId);

    /**
     *  更新Blog
     * @param blogVo
     * @param blogId
     * @return
     */
    Response<Blog> updateBlog(BlogVo blogVo, Long blogId);

    /**
     *  通过id查询
     * @param id
     * @return
     */
    Response<EsBlog> getBlogById(Long id);

    /**
     *  最热的列表
     * @param keyword
     * @param pageable
     * @return
     */
    Response<List<EsBlog>> listHotestBlogs(String keyword, Pageable pageable);

    /**
     *  最新的列表
     * @param keyword
     * @param pageable
     * @return
     */
    Response<List<EsBlog>> listNewestBlogs(String keyword, Pageable pageable);

    /**
     *  列出所有用户的博客列表
     * @param pageable
     * @return
     */
    Response<List<SimpleBlogVo>> listBlogs(Pageable pageable);

    /**
     *  列出单个用户的博客列表
     * @param userId
     * @param pageable
     * @return
     */
    Response<List<SimpleBlogVo>> listBlogsByUserId(Long userId, Pageable pageable);

}
