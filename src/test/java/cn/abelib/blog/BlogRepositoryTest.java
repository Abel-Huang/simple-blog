package cn.abelib.blog;


import cn.abelib.blog.domain.Blog;
import cn.abelib.blog.repository.BlogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by abel on 2017/12/28.
 *  es的资源库接口测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest{
    @Autowired
    private BlogRepository blogRepository;
    @Before
    public void initBlogRepositoryData(){
        blogRepository.deleteAll();
        blogRepository.save(new Blog("静夜思", "李白", "床前明月光，疑是地上霜。举头望明月，低头思故乡。"));
        blogRepository.save(new Blog("赋得古原草送别", "白居易", "离离原上草，一岁一枯荣。野火烧不尽，春风吹又生。"));
        blogRepository.save(new Blog("梅", "王安石", "墙角数枝梅，凌寒独自开。 遥知不是雪，为有暗香来。"));
    }
    @Test
    public void  findDistinctByTitleContainingOrSummaryContainingOrContentContaining(){
        Pageable pageable = new PageRequest(0, 20);
        String title = "草";
        String summary = "白";
        String content = "夜";
        Page<Blog> page = blogRepository.findDistinctByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
        assertThat(page.getTotalElements()).isEqualTo(2);
        for (Blog blog : page.getContent()){
            System.err.println(blog.toString());
        }
    }
//    @After
//    public void deleteBlogRepositoryData() {
//        blogRepository.deleteAll();
//    }
}
