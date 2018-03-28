package cn.abelib.blog.controller;



import cn.abelib.blog.common.result.Meta;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.pojo.Blog;
import cn.abelib.blog.pojo.Category;
import cn.abelib.blog.pojo.EsBlog;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.CategoryService;
import cn.abelib.blog.vo.BlogVo;
import cn.abelib.blog.vo.SimpleBlogVo;
import cn.abelib.blog.vo.TagVO;
import cn.abelib.blog.service.EsBlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by abel on 2017/11/5.
 *  博客控制器
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    /**
     *  最热博客列表
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/hotest")
    public Response<List<EsBlog>> listHotest(@RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                               @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Sort sort = new Sort(Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        return blogService.listHotestBlogs(keyword, pageable);
    }

    /**
     *  最新博客列表
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/newest")
    public Response listNewest(@RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                               @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Sort sort = new Sort(Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        return blogService.listNewestBlogs(keyword, pageable);
    }

    /**
     *  获取用户的所有博客列表, 注意这里是不会显示出博客的具体内容
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/list_blog")
    public Response<List<SimpleBlogVo>> listBlogs(@RequestParam(value = "pageIndex", required = false, defaultValue = "0")int pageIndex,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10")int pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        return blogService.listBlogs(pageable);
    }

    /**
     *  获取指定用户的所有博客列表, 注意这里是不会显示出博客的具体内容
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/list_user_blog")
    public Response<List<SimpleBlogVo>> listUserBlogs(Long userId,
                                                      @RequestParam(value = "pageIndex", required = false, defaultValue = "0")int pageIndex,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10")int pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        return blogService.listBlogsByUserId(userId, pageable);
    }

    /**
     *  获取指定用户的所有博客列表, 注意这里是不会显示出博客的具体内容
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/list_current_user_blog")
    public Response<List<SimpleBlogVo>> listUserBlogs(HttpSession session,
                                                      @RequestParam(value = "pageIndex", required = false, defaultValue = "0")int pageIndex,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize");
            Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
            return blogService.listBlogsByUserId(user.getId(), pageable);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  通过id获取博客, 是从ES中获取
     * @param id
     * @return
     */
    @GetMapping("/get_blog")
    public Response<EsBlog> getBlogById(HttpSession session, Long id){
        return blogService.getBlogById(id);
    }

    /**
     *  删除博客
     * @param id
     * @return
     */
    @PostMapping(value = "/delete_blog")
    public Response deleteBlog(HttpSession session, Long id){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return blogService.removeBlog(user.getId(), id);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  保存博客
     * @param session
     * @param blogVo
     * @return
     */
    @PostMapping(value = "/save_blog")
    public Response<Blog> saveBlog(HttpSession session, BlogVo blogVo){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return blogService.saveBlog(blogVo);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }
}
