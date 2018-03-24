package cn.abelib.blog.service.impl;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.exception.GlobalException;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.common.util.MD5Util;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.repository.UserRepository;
import cn.abelib.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by abel on 2017/11/7.
 * 用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    /**
     *  用户登录接口
     * @param userName
     * @param userPassword
     * @return
     */
    @Override
    public Response<User> login(String userName, String userPassword) {
        Integer resultCount = userRepository.countByUsername(userName);
        if (resultCount == 0){
            throw new GlobalException(StatusConstant.ACCOUNT_ALREADY_EXISTS);
        }
        //  这里需要进行密码的转换
        User user = userRepository.findByUsername(userName);
        String dbPassword = MD5Util.dbPassword(userPassword);

        if (!dbPassword.equals(user.getPassword())){
            throw new GlobalException(StatusConstant.INSERT_USER_ERROR);
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return Response.success(StatusConstant.GENERAL_SUCCESS, user);
    }

    /**
     *  注册用户
     * @param user
     * @return
     */
    @Override
    public Response<String> register(User user) {
        Integer resultCount = userRepository.countByUsername(user.getUsername());
        if (resultCount > 0){
            return Response.failed(StatusConstant.ACCOUNT_ALREADY_EXISTS);
        }
        user.setRole(StatusConstant.Role.ROLE_CUSTOMER);
        String dbPassword = MD5Util.dbPassword(user.getPassword());
        user.setPassword(dbPassword);
        user.setRole(StatusConstant.Role.ROLE_CUSTOMER);

        User saveUser = userRepository.save(user);
        if (saveUser == null){
            return Response.failed(StatusConstant.INSERT_USER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    @Override
    public Response<String> resetPassword(String originalPass, String newPassword, User user) {
        int resultCount = userRepository.countUserByIdAndPassword(user.getId(), MD5Util.dbPassword(originalPass));
        if (resultCount == 0){
            return Response.failed(StatusConstant.WRONG_PASS_ERROR);
        }
        user.setPassword(MD5Util.dbPassword(newPassword));

        User updateUser = userRepository.save(user);
        if (updateUser == null){
            return Response.failed(StatusConstant.UPDATE_PASS_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    @Override
    public Response<User> updateUserInfo(User user) {
        User updateUser = userRepository.findOne(user.getId());
        updateUser.setNickname(user.getNickname());
        updateUser.setEmail(user.getEmail());
        updateUser.setAvatar(user.getAvatar());
        updateUser.setRole(user.getRole());

        User saveUser = userRepository.save(user);
        if (saveUser == null){
            return Response.failed(StatusConstant.UPDATE_PASS_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    @Override
    public Response<String> checkAuthorRole(User user) {
        if (user != null && user.getRole() == StatusConstant.Role.ROLE_AUTHER){
            return Response.success(StatusConstant.GENERAL_SUCCESS);
        }
        return Response.failed(StatusConstant.GENERAL_SERVER_ERROR);
    }
}
