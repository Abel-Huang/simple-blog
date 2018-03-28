package cn.abelib.blog.service.impl;

import cn.abelib.blog.pojo.EsBlog;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.repository.EsBlogRepository;
import cn.abelib.blog.service.EsBlogService;
import cn.abelib.blog.vo.TagVO;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.SearchException;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

/**
 * Created by abel on 2017/11/17.
 *  EsBlog服务实现类
 */
@Service
public class EsBlogServiceImpl implements EsBlogService{
    @Autowired
    private EsBlogRepository esBlogRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

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
    public List<EsBlog> listNewestBlogs(String keyword, Pageable pageable) throws SearchException{
        Page<EsBlog> pages;
        // 按创建时间排序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        if (pageable.getSort() == null){
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
        return pages.getContent();
    }

    @Override
    public List<EsBlog> listHotestBlogs(String keyword, Pageable pageable) throws SearchException{
        Page<EsBlog> pages;
        // 按阅读量 评论量 点赞量 创建时间进行排序
        Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
        if (pageable.getSort() == null){
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        pages = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
        return pages.getContent();
    }

    @Override
    public List<EsBlog> listTopNewestBlogs(Integer N) {
        return this.listHotestBlogs("", new PageRequest(0, N));
    }

    @Override
    public List<EsBlog> listTopHotestBlogs(Integer N) {
        return this.listHotestBlogs("", new PageRequest(0, N));
    }

    /**
     *  最热门标签
     * @param N
     * @return
     */
    @Override
    public List<TagVO> listTopTags(Integer N) {
        List<TagVO> voList = new ArrayList<>();

        // 聚合,
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("blog")
                .withTypes("blog")
                .addAggregation(
                        terms("tags").field("tags")
                                .order(Terms.Order.count(false)).size(N)
                ).build();
        // 使用聚合的结果作为查询条件进行查询
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse searchResponse) {
                return searchResponse.getAggregations();
            }
        });

        // 获取查询结果
        StringTerms modelTerms = (StringTerms) aggregations.asMap().get("tags");
        Iterator<Terms.Bucket> modelBucket = modelTerms.getBuckets().iterator();
        while (modelBucket.hasNext()){
            Terms.Bucket bucket = modelBucket.next();
            voList.add(new TagVO(bucket.getKey().toString(), bucket.getDocCount()));
        }
        return voList;
    }
    @Override
    public List<User> listTopUsers(Integer N) {
        List<String> list = new ArrayList<>();

        // 聚合,
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("blog")
                .withTypes("blog")
                .addAggregation(
                        terms("users").field("username")
                                .order(Terms.Order.count(false)).size(N)
                ).build();
        // 使用聚合的结果作为查询条件进行查询
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse searchResponse) {
                return searchResponse.getAggregations();
            }
        });
        // 获取查询结果
        StringTerms modelTerms = (StringTerms) aggregations.asMap().get("users");
        Iterator<Terms.Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()){
            Terms.Bucket bucket = modelBucketIt.next();
            String username = bucket.getKey().toString();
            list.add(username);
        }
//        return  userService.listUsersByUsername(list);
        return null;
    }

    @Override
    public List<EsBlog> listEsBlogs(Pageable pageable) {
        return esBlogRepository.findAll(pageable).getContent();
    }
}
