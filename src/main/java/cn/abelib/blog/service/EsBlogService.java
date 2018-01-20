package cn.abelib.blog.service;

import cn.abelib.blog.domain.EsBlog;
import cn.abelib.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by abel on 2017/11/17.
 */
public interface EsBlogService {

    /**
     *  删除博客
     * @param id
     */
    void removeEsBlog(String id);

    /**
     *  更新博客
     * @param esBlog
     * @return
     */
    EsBlog updateEsBlog(EsBlog esBlog);

    /**
     *  通过Blog id获取EsBlog
     * @param blogId
     * @return
     */
    EsBlog getEsBlogByBlogId(Long blogId);

    /**
     *  获取最新的博客列表
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listNewestBlogs(String keyword, Pageable pageable);

    /**
     *  获取最热的博客列表
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listHotestBlogs(String keyword, Pageable pageable);

    /**
     *  获取最新的前N位博客列表
     * @param N
     * @return
     */
    Page<EsBlog> listTopNewestBlogs(Integer N);

    /**
     *  获取最热的前N位博客列表
     * @param N
     * @return
     */
    Page<EsBlog> listTopHotestBlogs(Integer N);

    /**
     *  获取博客列表
     * @param pageable
     * @return
     */
    Page<EsBlog> listEsBlogs(Pageable pageable);
}
