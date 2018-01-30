package cn.abelib.blog.service;

import cn.abelib.blog.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abel on 2017/11/7.
 * 用户服务测试类
 * 测试通过
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void addUserTest(){
        User user = new User("HuangJinjin", "123@126.com", "huangjinjin", "122333", "/data/avator/huangjinjin.jpg");
        User saveUser = userService.addUser(user);
        System.err.println(saveUser.toString());
    }

    @Test
    public void removeUserTest(){
        userService.removeUser(6L);
    }

    @Test
    public void getUserByIdTest(){
        User user = userService.getUserById(7L);
        System.err.println(user.toString());
    }

    @Test
    public void getUsersListTest(){
        List<User> userList = userService.getUsersList();
        for (User user : userList){
            System.err.println(user.toString());
        }
    }

    @Test
    public void listUsersByNameLikeTest(){
        String name = "jin";
        Pageable pageable = new PageRequest(0, 5);
        Page<User> userPage = userService.listUsersByNameLike(name,  pageable);
        for (User user : userPage.getContent()){
            System.err.println(user.toString());
        }
    }

    @Test
    public void getUserByUsernameTest(){
        String username = "123";
        User user = userService.getUserByUsername(username);
        System.err.println(user.toString());
    }

    @Test
    public void listUsersByUsernameTest() {
        String[] strings = new String[]{"jin123", "123", "11"};
        List<String> list =  Arrays.asList(strings);
        List<User> userList = userService.listUsersByUsername(list);
        for (User user : userList) {
            System.err.println(user.toString());
        }
    }
}
