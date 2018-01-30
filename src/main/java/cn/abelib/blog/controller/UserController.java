package cn.abelib.blog.controller;

import cn.abelib.blog.util.exception.RegisterException;
import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.bean.User;
import cn.abelib.blog.service.UserService;
import cn.abelib.blog.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by abel on 2017/11/3.
 *  用户信息API类
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  查询用户信息，可以分页，也可以使用name进行模糊查询
     * @param pageIndex
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/query")
    public Response listAndPage(@RequestParam(value = "index", required = false, defaultValue = "0")int pageIndex,
                                @RequestParam(value = "size", required = false, defaultValue = "10")int pageSize,
                                @RequestParam(value = "name", required = false, defaultValue = "")String name
                                ){
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> userPage = userService.listUsersByNameLike(name, pageable);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(userPage.getContent(), meta);
    }
    /**
     * 通过id找到User
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Response view(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(user, meta);
    }

    /**
     *  遍历表中所有数据
     * @return
     */
    @GetMapping("/list")
    public Response listAll(){
        List<User> users = new ArrayList<>();
        users.addAll(userService.getUsersList());
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(users, meta);
    }

    /**
     *  新增用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Response addUser(@RequestBody User user){
        userService.addUser(user);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(user, meta);
    }

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Response deleteUser(@PathVariable("id") Long id){
        userService.removeUser(id);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        Response response = new Response(meta);
        return response;
    }

    /**
     *  注册接口
     * @return
     */
    @PostMapping("/register")
    public Response register(@RequestBody User user){
        Response response;
        Meta meta;
        try{
            userService.register(user);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }catch (RegisterException e){
            meta = new Meta(HttpConstant.REQUEST_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  登录接口
     * @return
     */
    @PostMapping("/login")
    public Response login(@RequestBody String username,
                          @RequestBody String password){
        Response response;
        Meta meta;
        if (userService.login(username, password)){
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }else {
            meta = new Meta(HttpConstant.REQUEST_ERR, HttpConstant.REQUEST_ERR_STR);
            response = new Response(meta);
        }
        return response;
    }
}
