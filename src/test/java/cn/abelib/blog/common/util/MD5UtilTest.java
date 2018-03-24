package cn.abelib.blog.common.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by abel on 2017/11/10.
 * 测试工具类中方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5UtilTest {
    @Test
    public void testGetMD5(){
        File file = new File(".gitignore");
        String fileMD5 = "";
        long start = 0L;
        long end = 0L;
        try {
            start = System.currentTimeMillis();
            fileMD5 = MD5Util.getMD5(new FileInputStream(file));
            end = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(fileMD5 + " spent " + (end-start) + " ms");
    }
}
