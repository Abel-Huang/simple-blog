package cn.abelib.blog.service;

import cn.abelib.blog.pojo.EsBlog;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.vo.TagVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by abel on 2017/11/17.
 * EsBlog的服务接口
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
    List<EsBlog> listNewestBlogs(String keyword, Pageable pageable);

    /**
     *  获取最热的博客列表
     * @param keyword
     * @param pageable
     * @return
     */
    List<EsBlog> listHotestBlogs(String keyword, Pageable pageable);

    /**
     *  获取最新的前N位博客列表
     * @param N
     * @return
     */
    List<EsBlog> listTopNewestBlogs(Integer N);

    /**
     *  获取最热的前N位博客列表
     * @param N
     * @return
     */
    List<EsBlog> listTopHotestBlogs(Integer N);

    /**
     *  获取最热的前N标签
     * @param N
     * @return
     */
    List<TagVO> listTopTags(Integer N);

    /**
     *  获取最热的前N用户
     * @param N
     * @return
     */
    List<User> listTopUsers(Integer N);

    /**
     *  获取博客列表
     * @param pageable
     * @return
     */
    List<EsBlog> listEsBlogs(Pageable pageable);
}
