package cn.abelib.blog.controller;



import cn.abelib.blog.bean.EsBlog;
import cn.abelib.blog.bean.User;
import cn.abelib.blog.service.EsBlogService;
import cn.abelib.blog.util.http.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by abel on 2017/11/5.
 *  博客控制器
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private EsBlogService esBlogService;

    @GetMapping("/hotest")
    public Response listHotest(@RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                         @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Sort sort = new Sort(Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        List<EsBlog> esBlogs = esBlogService.listHotestBlogs(keyword, pageable);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(esBlogs, meta);
    }

    @GetMapping("/newest")
    public Response listNewest(@RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                               @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Sort sort = new Sort(Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        List<EsBlog> esBlogs = esBlogService.listNewestBlogs(keyword, pageable);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(esBlogs, meta);
    }

    @GetMapping("/list/users")
    public Response listUsers(@RequestParam(value = "num", required = false, defaultValue = "10")Integer num){
        List<User> list = esBlogService.listTopUsers(num);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(list, meta);
    }

    @GetMapping("/list/tags")
    public Response listTags(@RequestParam(value = "num", required = false, defaultValue = "10")Integer num){
        List<TagVO> list = esBlogService.listTopTags(num);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(list, meta);
    }

    @GetMapping("/list/hotest")
    public Response listHotest(@RequestParam(value = "num", required = false, defaultValue = "10")Integer num){
        List<EsBlog> list = esBlogService.listTopHotestBlogs(num);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(list, meta);
    }

    @GetMapping("/list/newest")
    public Response listNewest(@RequestParam(value = "num", required = false, defaultValue = "10")Integer num){
        List<EsBlog> list = esBlogService.listTopNewestBlogs(num);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(list, meta);
    }

}
