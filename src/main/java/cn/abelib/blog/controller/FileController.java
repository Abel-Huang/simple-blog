package cn.abelib.blog.controller;

import cn.abelib.blog.pojo.File;
import cn.abelib.blog.service.FileService;
import cn.abelib.blog.common.util.MD5Util;
import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.result.Response;
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
 * Created by abel on 2017/11/6.
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
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    public Response getFiles(@PathVariable int pageIndex, @PathVariable int pageSize){
        List<File> fileList = fileService.listFileByPage(pageIndex, pageSize);
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
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
                    .body( Response.failed(StatusConstant.GENERAL_SUCCESS));
        }else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Response.failed(StatusConstant.GENERAL_SUCCESS));
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
                    .body(Response.failed(StatusConstant.GENERAL_SUCCESS));
    }

    /**
     *  上传接口
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public Response upload(@RequestParam("file")MultipartFile multipartFile){
        File file;
        File f;


        try {
            f = new File(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getSize(), multipartFile.getBytes());
            f.setMd5(MD5Util.getMd5((FileInputStream)multipartFile.getInputStream(), multipartFile.getSize()));
            file = fileService.addFile(f);
            String path = "//" + serverAddress + ":" + serverPort + "/view/" + file.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
    }

    /**
     *  删除文件
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable String id){
        fileService.removeFile(id);
        return Response.failed(StatusConstant.GENERAL_SUCCESS);
    }
}
