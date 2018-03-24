package cn.abelib.blog.service;

import cn.abelib.blog.pojo.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by abel on 2017/11/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {
    @Autowired
    private BlogService blogService;

    @Test
    public void saveBlogTest(){
        String title = "ElasticSearch Java API";
        String summary = "ElasticSearch Java API in Action";
        String content = "ElasticSearch is very popular search engine" +
                "and more and people use";
        Blog blog = new Blog(title, summary, content);
        Blog saveBlog = blogService.saveBlog(blog);
        System.err.println(saveBlog);
    }

    @Test
    public void updateBlogTest(){

    }

    @Test
    public void getBlogByIdTest(){
        Blog blog = blogService.getBlogById(1L);
        System.err.println(blog);
    }

    @Test
    public void readingIncrease(){
        int  oldValue = blogService.getBlogById(1L).getReadSize();
        blogService.readingIncrease(1L);
        int  newValue = blogService.getBlogById(1L).getReadSize();
        assertThat(oldValue + 1,equalTo(newValue));
    }

    @Test
    public void createCommentTest(){

    }
}
