package cn.abelib.blog.service;

import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.common.exception.GlobalException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by abel on 2017/11/7.
 * 用户服务接口, 定义了用户的常见操作
 */
public interface UserService {
    Response<User> login(String userName, String userPassword);

    Response<String> register(User user);

    Response<String> resetPassword(String originalPass, String newPassword, User user);

    Response<User> updateUserInfo(User user);

    Response<String> checkAuthorRole(User user);
}
