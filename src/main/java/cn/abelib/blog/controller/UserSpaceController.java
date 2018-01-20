package cn.abelib.blog.controller;

import cn.abelib.blog.domain.Blog;
import cn.abelib.blog.domain.User;
import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.UserService;
import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by abel on 2017/11/7.
 * 用户主页控制器
 */
@RestController
@RequestMapping("/u")
public class UserSpaceController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/{userName}")
    public Response profile(@PathVariable("userName") String username){
        return null;
    }

    /**
     *  获取用户个人设置
     * @param username
     * @return
     */
    @GetMapping("/{username}/profile")
    public Response getProfile(@PathVariable("username")String username){
        User user = userService.getUserByUsername(username);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(user, meta);
    }

    /**
     *  保存个人设置
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/profile")
    public Response saveProfile(@PathVariable("username")String username, User user){
        Meta meta;
        Response response;
        User oldUser = userService.getUserByUsername(username);
        oldUser.setEmail(user.getEmail());
        oldUser.setName(user.getName());

        // 判断用户密码是否做了变更
        String rawPassword = oldUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePassword);
        if (!isMatch){
//            oldUser.set
        }
        userService.addUser(oldUser);
        meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        response = new Response(meta);
        return response;
    }

    /**
     * 保存用户头像
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/avatar")
    public Response saveAvatar(@PathVariable("username")String username, @RequestBody User user){
        Meta meta;
        Response response;
        String avatarUrl = user.getAvatar();

        User oldUser = userService.getUserById(user.getId());
        oldUser.setAvatar(avatarUrl);
        userService.updateUser(oldUser);
        meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        response = new Response(meta, avatarUrl);
        return response;
    }


    /**
     *  获取用户的所有博客列表, 注意这里是不会显示出博客的具体内容
     * @param username
     * @param order
     * @param category
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/{username}/blogs")
    public Response listBlogs(@PathVariable("username")String username,
                              @RequestParam(value = "order", required = false, defaultValue = "new")String order,
                              @RequestParam(value = "category", required = false)Long category,
                              @RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "0")int pageIndex,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10")int pageSize){
        Meta meta;
        Response response;
        User user = userService.getUserByUsername(username);

        Page<Blog> page = null;
        if (order.equals("hot")){
            Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize");
            Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
            page = blogService.listBlogByTitleLikeAndSort(user, keyword, pageable);

        }
        if (order.equals("new")){
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = blogService.listBlogByTitleLikeAndSort(user, keyword, pageable);
        }

        // 只保留博客的标题和简介
        List<Blog> blogList = page.getContent();
        for (int i = 0; i < blogList.size(); i++){
            blogList.add(i, blogList.get(i).simpleBlog());
        }

        meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        response = new Response(meta, blogList);
        return response;
    }

    /**
     *  通过id获取博客
     * @param id
     * @return
     */
    @GetMapping("/{username}/blogs/{id}")
    public Response getBlogsById(@PathVariable("id")Long id){
        Meta meta;
        Response response;
        try{
            Blog blog = blogService.getBlogById(id);
            blogService.readingIncrease(id);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta, blog);
        }catch (Exception e){
            meta = new Meta(HttpConstant.SERVER_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  删除博客
     * @param id
     * @return
     */
    @PostMapping(value = "/{username}/blogs/{id}")
    public Response deleteBlog(@PathVariable("id") Long id){
        Meta meta;
        Response response;
        try{
            blogService.removeBlog(id);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }catch (Exception e){
            meta = new Meta(HttpConstant.SERVER_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  保存博客
     * @param username
     * @param blog
     * @return
     */
    @PostMapping(value = "/{username}/blogs/edit")
    public Response saveBlog(@PathVariable("username")String username, @RequestBody Blog blog){
        Meta meta;
        Response response;
        User user = userService.getUserByUsername(username);
        blog.setUser(user);
        try {
            blogService.saveBlog(blog);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        } catch (ConstraintViolationException ex){
            meta = new Meta(HttpConstant.REQUEST_ERR, ex.getMessage());
            response = new Response(meta);
        } catch (Exception e){
            meta = new Meta(HttpConstant.SERVER_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }
}
