package cn.abelib.blog.service.impl;

import cn.abelib.blog.bean.*;
import cn.abelib.blog.repository.BlogRepository;
import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abel on 2017/11/11.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private EsBlogService esBlogService;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.delete(id);
    }

    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Page<Blog> listBlogByTitleLike(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogByTitleLikeAndSort(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setReadSize(blog.getReadSize() + 1);
        blogRepository.save(blog);
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        Blog originalBlog = blogRepository.findOne(blogId);
        User user = originalBlog.getUser();
        Comment comment = new Comment(user, commentContent);
        originalBlog.addComment(comment);
        return this.saveBlog(originalBlog);
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        originalBlog.removeComment(commentId);
        this.saveBlog(originalBlog);
    }

    @Override
    public Blog createVote(Long blogId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        User user = originalBlog.getUser();
        Vote vote = new Vote(user);
        boolean isExist = originalBlog.addVote(vote);
        if (isExist){
            throw new IllegalArgumentException("已经点赞过了");
        }
        return this.saveBlog(originalBlog);
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        originalBlog.removeVote(voteId);
        this.saveBlog(originalBlog);
    }

    @Override
    public Page<Blog> listBlogByCategory(Category category, Pageable pageable) {
        return blogRepository.findByCategory(category, pageable);
    }
}
