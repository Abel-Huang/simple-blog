package cn.abelib.blog.service;

import cn.abelib.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by abel on 2018/1/7.
 * 用户服务接口, 定义了用户的常见操作
 */
public interface UserService {
    /**
     * 保存用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long  id);

    /**
     * 批量删除用户
     * @param userList
     */
    void removeUsers(List<User> userList);

    /**
     * 更新用户
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> getUsersList();

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
