package cn.abelib.blog.controller;

import cn.abelib.blog.domain.File;
import cn.abelib.blog.domain.User;
import cn.abelib.blog.repository.UserRepository;
import cn.abelib.blog.service.FileService;
import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.util.http.ResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件控制器API
 * Created by abel on 2018/1/6.
 */
// 允许所有域名访问
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("localhost")
    private String serverAddress;

    @Value("8080")
    private String serverPort;

    /**
     *  返回最新的20条数据
     * @return
     */
    @GetMapping("/")
    public Response getLatest(){
        List<File> fileList = fileService.listFileByPage(0, 20);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseTool.validator(fileList, meta);
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    public Response getFiles(@PathVariable int pageIndex, @PathVariable int pageSize){
        List<File> fileList = fileService.listFileByPage(pageIndex, pageSize);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseTool.validator(fileList, meta);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable String id){
        File file = fileService.getFileById(id);
        if (file.isEmpty()){

        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable String id){
        fileService.removeFile(id);
        return null;
    }

}
