package cn.abelib.blog.controller;

import cn.abelib.blog.util.http.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by abel on 2018/1/7.
 * 用户主页控制器
 */
@RestController
@RequestMapping("/u")
public class UserSpaceController {
    @GetMapping("/{userName}")
    public Response profile(@PathVariable("userName") String username){
        return null;
    }
}
