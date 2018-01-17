package cn.abelib.blog.service.impl;

import cn.abelib.blog.domain.EsBlog;
import cn.abelib.blog.repository.EsBlogRepository;
import cn.abelib.blog.service.EsBlogService;
import cn.abelib.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by abel on 2018/1/17.
 */
@Service
public class EsBlogServiceImpl implements EsBlogService{
    @Autowired
    private EsBlogRepository esBlogRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserService userService;

    @Override
    public void removeEsBlog(String id) {
        esBlogRepository.delete(id);
    }

    @Override
    public EsBlog updateEsBlog(EsBlog esBlog) {
        return esBlogRepository.save(esBlog);
    }

    @Override
    public EsBlog getEsBlogByBlogId(Long blogId) {
        return esBlogRepository.findEsBlogByBlogId(blogId);
    }

    @Override
    public Page<EsBlog> listNewestBlogs(String keyword, Pageable pageable) {
        Page<EsBlog> pages;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        if (pageable.getSort() == null){
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
        return pages;
    }

    @Override
    public Page<EsBlog> listHotestBlogs(String keyword, Pageable pageable) {
        Page<EsBlog> pages;
        Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
        if (pageable.getSort() == null){
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
        return pages;
    }

    @Override
    public Page<EsBlog> listTopNewestBlogs(Integer N) {
        return null;
    }

    @Override
    public Page<EsBlog> listTopHotestBlogs(Integer N) {
        return null;
    }

    @Override
    public Page<EsBlog> listEsBlogs(Pageable pageable) {
        return esBlogRepository.findAll(pageable);
    }
}
