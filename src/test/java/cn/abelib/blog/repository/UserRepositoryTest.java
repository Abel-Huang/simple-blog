package cn.abelib.blog.repository;

import cn.abelib.blog.common.util.MD5Util;
import cn.abelib.blog.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abel on 2018/1/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     *  success
     */
    @Test
    public void countByUsernameTest(){
        String userName = "abel@126.com1";
        Integer resultCount = userRepository.countByUsername(userName);
        System.err.println(resultCount);
    }

    /**
     *  success
     */
    @Test
    public void insertTest(){
        String name = "Abel";
        String email = "abel@126.com";
        String userName = "abel@126.com";
        String password = "123456";
        Integer role = 1;
        String avatar = "/avatar/abel/1.jpg";
        User user = new User(name, email, userName, password, role, avatar);
        User insertUser = userRepository.save(user);
        System.err.println(insertUser.toString());
    }

    /**
     *  success
     */
    @Test
    public void updateTest(){
        Long id = 1l;
        User user = userRepository.findOne(id);
        String password = "123456";
        user.setPassword(MD5Util.dbPassword(password));
        User insertUser = userRepository.save(user);
        System.err.println(insertUser.toString());
    }



    @Test
    public void findUserByUsernameTest(){
        String username = "abel@126.com";
        User user = userRepository.findByUsername(username);
        System.err.println(user.toString());
    }

    @Test
    public void countUserByIdAndPasswordTest(){
        Integer id = 1;
        String password = "123456";
    }

}
