package cn.abelib.blog.service.impl;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.exception.GlobalException;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.*;
import cn.abelib.blog.repository.BlogRepository;
import cn.abelib.blog.repository.UserRepository;
import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.EsBlogService;
import cn.abelib.blog.service.UserService;
import cn.abelib.blog.vo.BlogVo;
import cn.abelib.blog.vo.SimpleBlogVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by abel on 2017/11/11.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private EsBlogService esBlogService;
    @Autowired
    private UserRepository userRepository;

    /**
     *  需要同时将数据存储到MySQL和ES中，修改中也需要同时对两者进行修改,
     *  后续会考虑从MySQL中直接迁移数据到ES
     * @param blogVo
     * @return
     */
    @Override
    public Response<Blog> saveBlog(BlogVo blogVo) {
        if (blogVo.isNotFull()){
            throw new GlobalException(StatusConstant.PRAM_BIND_ERROR);
        }
        Blog blog = assembleBlog(blogVo);
        Blog saveBlog = blogRepository.save(blog);
        if (saveBlog == null){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        EsBlog esBlog = assembleEsBlog(blogVo, saveBlog.getId());
        EsBlog saveEsBlog = esBlogService.updateEsBlog(esBlog);
        if (saveEsBlog == null){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, saveBlog);
    }

    /**
     *  组装EsBlog
     * @param blogVo
     * @return
     */
    private EsBlog assembleEsBlog(BlogVo blogVo, Long blogId){
        EsBlog esBlog = new EsBlog(blogVo.getTitle(), blogVo.getSummary(), blogVo.getContent(), blogVo.getTags());
        esBlog.setBlogId(blogId);
        User user = userRepository.findOne(blogVo.getUserId());
        if (user == null)
            return null;
        String username = user.getUsername();
        esBlog.setUsername(username);
        esBlog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        esBlog.setReadSize(0);
        esBlog.setCommentSize(0);
        esBlog.setVoteSize(0);
        esBlog.setTags(blogVo.getTags());
       return esBlog;
    }

    /**
     *  组装Blog
     * @param blogVo
     * @return
     */
    private Blog assembleBlog(BlogVo blogVo){
        Blog blog = new Blog(blogVo.getTitle(), blogVo.getSummary(), blogVo.getContent());
        blog.setUserId(blogVo.getUserId());
        blog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        blog.setReadSize(0);
        blog.setCommentSize(0);
        blog.setVoteSize(0);
        blog.setTags(blogVo.getTags());
        blog.setCategoryId(blogVo.getCategoryId());
        return blog;
    }

    /**
     *  删除博客，需要从DB和ES都删除
     * @param blogId
     */
    @Override
    public Response removeBlog(Long userId, Long blogId) {
        if (userId == null || blogId == null){
            throw new GlobalException(StatusConstant.PRAM_BIND_ERROR);
        }
        Integer resultCount = blogRepository.deleteByIdAndUserId(blogId, userId);
        if (resultCount == 0){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        EsBlog esBlog = esBlogService.getEsBlogByBlogId(blogId);
        if (esBlog == null){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        esBlogService.removeEsBlog(esBlog.getId());
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    /**
     *  更新Blog
     * @param blogVo
     * @param blogId
     * @return
     */
    @Override
    public Response<Blog> updateBlog(BlogVo blogVo, Long blogId) {
        if (blogVo == null || blogId == null){
            throw new GlobalException(StatusConstant.PRAM_BIND_ERROR);
        }
        Blog blog = blogRepository.findOne(blogId);
        EsBlog esBlog = esBlogService.getEsBlogByBlogId(blogId);
        if (blog == null || esBlog == null){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        Blog updateBlog = this.updateBlog(blog, blogVo);
        EsBlog updateEsBlog = this.updateEsBlog(esBlog, blogVo);
        updateBlog =  blogRepository.save(updateBlog);
        updateEsBlog = esBlogService.updateEsBlog(updateEsBlog);
        if (updateBlog == null || updateEsBlog == null){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, updateBlog);
    }

    /**
     *  更新EsBlog
     * @param esBlog
     * @param blogVo
     * @return
     */
    private EsBlog updateEsBlog(EsBlog esBlog, BlogVo blogVo){
        if (blogVo.isNotFull()){
            return esBlog;
        }
        if (blogVo.getContent() != null){
            esBlog.setContent(blogVo.getContent());
        }
        if (blogVo.getSummary() != null){
            esBlog.setSummary(blogVo.getSummary());
        }
        if (blogVo.getTags() != null){
            esBlog.setTags(blogVo.getTags());
        }
        if (blogVo.getTitle() != null){
            esBlog.setTitle(blogVo.getTitle());
        }
        return esBlog;
    }

    /**
     *  更新Blog
     * @param blog
     * @param blogVo
     * @return
     */
    private Blog updateBlog(Blog blog, BlogVo blogVo){
        if (!blogVo.isNotFull()){
            return blog;
        }
        if (blogVo.getContent() != null){
            blog.setContent(blogVo.getContent());
        }
        if (blogVo.getSummary() != null){
            blog.setSummary(blogVo.getSummary());
        }
        if (blogVo.getTags() != null){
            blog.setTags(blogVo.getTags());
        }
        if (blogVo.getTitle() != null){
            blog.setTitle(blogVo.getTitle());
        }
        if (blogVo.getCategoryId() != null){
            blog.setCategoryId(blogVo.getCategoryId());
        }
        if (blogVo.getUserId() != null){
            blog.setUserId(blogVo.getUserId());
        }
        return blog;
    }

    /**
     *  通过id获得
     * @param id
     * @return
     */
    @Override
    public Response<EsBlog> getBlogById(Long id) {
        if (id == null){
            throw new GlobalException(StatusConstant.PRAM_BIND_ERROR);
        }
        EsBlog esBlog = esBlogService.getEsBlogByBlogId(id);
        if (esBlog == null){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, esBlog);
    }


    @Override
    public Response<List<SimpleBlogVo>> listBlogs(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        if (CollectionUtils.isEmpty(blogs.getContent())){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        List<SimpleBlogVo> simpleBlogVos = Lists.newArrayList();
        for (Blog blog : blogs.getContent()){
            simpleBlogVos.add(assembleEsBlog(blog));
        }
        if (CollectionUtils.isEmpty(simpleBlogVos)){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, simpleBlogVos);
    }

    @Override
    public Response<List<SimpleBlogVo>> listBlogsByUserId(Long userId, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findBlogsByUserId(userId, pageable);
        if (CollectionUtils.isEmpty(blogs.getContent())){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        List<SimpleBlogVo> simpleBlogVos = Lists.newArrayList();
        for (Blog blog : blogs.getContent()){
            simpleBlogVos.add(assembleEsBlog(blog));
        }
        if (CollectionUtils.isEmpty(simpleBlogVos)){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, simpleBlogVos);
    }

    private SimpleBlogVo assembleEsBlog(Blog blog){
        SimpleBlogVo simpleBlogVo = new SimpleBlogVo();
        simpleBlogVo.setId(blog.getId());
        simpleBlogVo.setSummary(blog.getSummary());
        simpleBlogVo.setTitle(blog.getTitle());
        String[] tags = blog.getTags().split(",");
        simpleBlogVo.setTags(tags);
        return simpleBlogVo;
    }


    /**
     *  列出当前最热的博客列表
     * @param keyword
     * @param pageable
     * @return
     */
    public Response<List<EsBlog>> listHotestBlogs(String keyword, Pageable pageable){
        List<EsBlog> esBlogList = esBlogService.listHotestBlogs(keyword, pageable);
        if (CollectionUtils.isEmpty(esBlogList)){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, esBlogList);
    }

    /**
     *  列出当前最新的博客列表
     * @param keyword
     * @param pageable
     * @return
     */
    public Response<List<EsBlog>> listNewestBlogs(String keyword, Pageable pageable){
        List<EsBlog> esBlogList = esBlogService.listNewestBlogs(keyword, pageable);
        if (CollectionUtils.isEmpty(esBlogList)){
            throw new GlobalException(StatusConstant.BLOG_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, esBlogList);
    }
}
