package cn.abelib.blog.repository;

import cn.abelib.blog.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by abel on 2017/12/28.
 *  es的资源库接口
 */
public interface BlogRepository extends ElasticsearchRepository<Blog, String>{
    Page<Blog> findDistinctByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
}
