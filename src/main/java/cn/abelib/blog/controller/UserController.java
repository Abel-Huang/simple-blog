package cn.abelib.blog.controller;

import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.bean.User;
import cn.abelib.blog.service.UserService;
import cn.abelib.blog.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        for (User user : userService.getUsersList()){
            users.add(user);
        }
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
     *  修改用户信息API
     *  会返回当前查询id的用户信息
     * @param id
     * @return
     */
    @GetMapping("edit/{id}")
    public Response update(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        Meta meta = new Meta(200, "success");
        return ResponseUtil.validator(user, meta);
    }
}
