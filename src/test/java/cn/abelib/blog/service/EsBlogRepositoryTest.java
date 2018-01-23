package cn.abelib.blog.service;



import cn.abelib.blog.bean.EsBlog;
import cn.abelib.blog.repository.EsBlogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * Created by abel on 2017/11/28.
 *  EsBlog 仓库测试类
 *  测试通过
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {
    @Autowired
    private EsBlogRepository blogRepository;
    @Before
    public void initBlogRepositoryData(){
        blogRepository.deleteAll();
        blogRepository.save(new EsBlog("静夜思", "李白", "床前明月光，疑是地上霜。举头望明月，低头思故乡。", "李白 月 思乡"));
        blogRepository.save(new EsBlog("赋得古原草送别", "白居易", "离离原上草，一岁一枯荣。野火烧不尽，春风吹又生。", "草 送别"));
        blogRepository.save(new EsBlog("梅", "王安石", "墙角数枝梅，凌寒独自开。 遥知不是雪，为有暗香来。", "梅 雪"));
    }
    @Test
    public void  findDistinctByTitleContainingOrSummaryContainingOrContentContaining(){
        Pageable pageable = new PageRequest(0, 20);
        String title = "草";
        String summary = "白";
        String content = "夜";
        String tag = "";
        Page<EsBlog> page = blogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(title, summary, content, tag, pageable);
        for (EsBlog blog : page.getContent()){
            System.err.println(blog.toString());
        }
    }

}
