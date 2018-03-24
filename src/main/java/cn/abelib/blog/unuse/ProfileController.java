package cn.abelib.blog.unuse;


import cn.abelib.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by abel on 2017/11/7.
 * 用户主页控制器
 */
@RestController
@RequestMapping("/portal/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

}
