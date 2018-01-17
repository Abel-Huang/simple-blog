package cn.abelib.blog.util;


import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by abel on 2018/1/10.
 * 测试工具类中方法
 */
public class UtilTest {
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
