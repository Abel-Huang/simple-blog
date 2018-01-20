package cn.abelib.blog.controller;



import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by abel on 2017/11/5.
 *  博客控制器
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping
    public Response list(@RequestParam(value = "title")String title,
                         @RequestParam(value = "summary")String summary,
                         @RequestParam(value = "content")String content,
                         @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return null;
    }
}
