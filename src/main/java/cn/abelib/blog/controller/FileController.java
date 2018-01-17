package cn.abelib.blog.controller;

import cn.abelib.blog.domain.File;
import cn.abelib.blog.service.FileService;
import cn.abelib.blog.util.MD5Util;
import cn.abelib.blog.util.http.HttpConstant;
import cn.abelib.blog.util.http.Meta;
import cn.abelib.blog.util.http.Response;
import cn.abelib.blog.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
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

    @Value("8081")
    private String serverPort;

    /**
     *  返回最新的20条数据
     * @return
     */
    @GetMapping("/")
    public Response getLatest(){
        List<File> fileList = fileService.listFileByPage(0, 20);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(fileList, meta);
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    public Response getFiles(@PathVariable int pageIndex, @PathVariable int pageSize){
        List<File> fileList = fileService.listFileByPage(pageIndex, pageSize);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(fileList, meta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getInfo(@PathVariable String id){
        File file = fileService.getFileById(id);
        File result;
        if (!file.isEmpty()){
            result = new File(file.getId(), file.getName(), file.getContentType(), file.getSize(),
                    file.getUploadDate(), file.getMd5(), file.getPath());
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream" )
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize()+"")
                    .header("Connection",  "close")
                    .body(new Response(new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR), result));
        }else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(new Meta(HttpConstant.RESPONSE_EMPTY, HttpConstant.FILE_EMPTY_STR)));
    }

    /**
     *  在线预览
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    public ResponseEntity<Object> viewFile(@PathVariable String id){
        File file =fileService.getFileById(id);
        if (!file.isEmpty()){
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName+\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .header("Connection", "close")
                    .body(file.getContent());
        }else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(new Meta(HttpConstant.RESPONSE_EMPTY, HttpConstant.FILE_EMPTY_STR)));
    }

    /**
     *  上传接口
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public Response upload(@RequestParam("file")MultipartFile multipartFile){
        File file;
        Meta meta;
        Response response;
        try {
            File f = new File(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getSize(), multipartFile.getBytes());
            f.setMd5(MD5Util.getMd5((FileInputStream)multipartFile.getInputStream(), multipartFile.getSize()));
            file = fileService.addFile(f);
            String path = "//" + serverAddress + ":" + serverPort + "/view/" + file.getId();
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta, path);
        }catch (IOException e){
            meta = new Meta(HttpConstant.SERVER_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  删除文件
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable String id){
        Meta meta;
        Response response;
        try{
            fileService.removeFile(id);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        }catch (Exception e){
            meta = new Meta(HttpConstant.SERVER_ERR,e.getMessage());
        }
        response = new Response(meta);
        return response;
    }
}
