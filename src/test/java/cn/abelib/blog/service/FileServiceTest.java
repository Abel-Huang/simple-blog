package cn.abelib.blog.service;

import cn.abelib.blog.pojo.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by abel on 2017/11/23.
 *  文件服务的测试类
 *  测试通过
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Test
    public void addFileTest(){
        String name = "Avatar Image";
        String contentType = "image/jpeg";
        long size = 100L;
        byte [] content = {11, 2, 5, 9, 4, 5, 3, 2, 4, 2, 'b', 7, 2, 0, 3, 2, 2, 'a', 12, 122};
        File file = new File(name, contentType, size, content);
        File saveFile = fileService.addFile(file);
        System.err.println(saveFile.getName() + " " + saveFile.getContentType());
    }

    @Test
    public void removeFileTest(){
        fileService.removeFile("5a6738ba345f1824042c532c");
    }

    @Test
    public void getFileByIdTest(){
        File saveFile = fileService.getFileById("5a6738ba345f1824042c532c");
        System.err.println(saveFile.getName() + " " + saveFile.getContentType());
    }

    @Test
    public void listFileByPageTest(){
        List<File> files = fileService.listFileByPage(1, 4);
        for (File file : files){
            System.err.println(file.getName() + " " + file.getContentType());
        }
    }


}
