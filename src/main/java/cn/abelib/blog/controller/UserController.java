package cn.abelib.blog.controller;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.result.Meta;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Blog;
import cn.abelib.blog.pojo.Category;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.service.BlogService;
import cn.abelib.blog.service.CategoryService;
import cn.abelib.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by abel on 2017/11/3.
 *  用户接口
 */
@RestController
@RequestMapping("/portal/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    /**
     *  登录
     * @param userName
     * @param userPassword
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public Response<User> login(String userName, String userPassword, HttpSession session){
        Response<User> response = userService.login(userName, userPassword);
        if (response.isSuccess()){
            session.setAttribute(StatusConstant.CURRENT_USER, response.getBody());
        }
        return response;
    }

    /**
     *  退出登录
     * @param session
     * @return
     */
    @GetMapping(value = "/logout")
    public Response logout(HttpSession session) {
        // 从session中移除用户信息
        session.removeAttribute(StatusConstant.CURRENT_USER);
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    /**
     *  用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public Response register(User user){
        return userService.register(user);
    }

    /**
     *  获取用户信息
     * @param session
     * @return
     */
    @GetMapping(value = "/user_info")
    public Response<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return Response.success(StatusConstant.GENERAL_SUCCESS, user);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  修改用户密码
     * @param session
     * @param originalPass
     * @param newPass
     * @return
     */
    @PostMapping(value = "/reset_password")
    public Response<String> resetPassword(HttpSession session, String originalPass, String newPass){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user == null){
            return Response.failed(StatusConstant.USER_NOT_LOGIN);
        }
        return userService.resetPassword(originalPass, newPass, user);
    }

    /**
     *  修改用户信息
     * @param session
     * @param user
     * @return
     */
    @PostMapping(value = "/update_user_info")
    public Response<User> updateUserInfo(HttpSession session, User user){
        User currentUser = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (currentUser == null){
            return Response.failed(StatusConstant.USER_NOT_LOGIN);
        }
        user.setId(currentUser.getId());
        user.setNickname(currentUser.getNickname());
        Response<User> response = userService.updateUserInfo(user);
        if (response.isSuccess()){
            response.getBody().setNickname(currentUser.getNickname());
            session.setAttribute(StatusConstant.CURRENT_USER, response.getBody());
        }
        return response;
    }

    /**
     *  todo
     * 保存用户头像
     * @param multipartFile
     * @return
     */
    @PostMapping("/avatar")
    public Response saveAvatar(HttpSession session, MultipartFile multipartFile){
        Meta meta;
        Response response;
//        String avatarUrl = user.getAvatar();

//        User oldUser = userService.getUserById(user.getId());
//        oldUser.setAvatar(avatarUrl);
//        userService.updateUser(oldUser);
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
    }

    /**
     *  获取用户的所有博客列表, 注意这里是不会显示出博客的具体内容
     * @param session
     * @param order
     * @param categoryId
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/{username}/blogs")
    public Response listBlogs(HttpSession session,
                              @RequestParam(value = "order", required = false, defaultValue = "new")String order,
                              @RequestParam(value = "category", required = false)Long categoryId,
                              @RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "0")int pageIndex,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10")int pageSize){

        User currentUser = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (currentUser == null){
            return Response.failed(StatusConstant.USER_NOT_LOGIN);
        }

        Page<Blog> page = null;

        if (categoryId != null && categoryId > 0){
            Category category = categoryService.getCategoryById(categoryId).getBody();
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = blogService.listBlogByCategory(category, pageable);
            order = "";
        }
        else if (order.equals("hot")){
            Sort sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize");
            Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
            //  page = blogService.listBlogByTitleLikeAndSort(user, keyword, pageable);

        }
        else if (order.equals("new")){
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            //  page = blogService.listBlogByTitleLikeAndSort(user, keyword, pageable);
        }

        // 只保留博客的标题和简介
        List<Blog> blogList = page.getContent();
        for (int i = 0; i < blogList.size(); i++){
            blogList.add(i, blogList.get(i).simpleBlog());
        }

        return Response.failed(StatusConstant.GENERAL_SUCCESS);
    }

    /**
     *  通过id获取博客
     * @param id
     * @return
     */
    @GetMapping("/{username}/blogs/{id}")
    public Response getBlogById(@PathVariable("id")Long id){

        Blog blog = blogService.getBlogById(id);
        blogService.readingIncrease(id);
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
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

        blogService.removeBlog(id);
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
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


        // 判断对于Blog的操作是修改还是新增
        if (blog.getId() != null){
            Blog originalBlog = blogService.getBlogById(blog.getId());
            originalBlog.setTitle(blog.getTitle());
            originalBlog.setContent(blog.getContent());
            originalBlog.setSummary(blog.getSummary());
            originalBlog.setCategoryId(blog.getCategoryId());
            originalBlog.setTags(originalBlog.getTags());
            blogService.saveBlog(originalBlog);
        }else {
            //   User user = userService.getUserByUsername(username);
//                blog.setUser(user);
//                blogService.saveBlog(blog);
        }
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
    }
}
