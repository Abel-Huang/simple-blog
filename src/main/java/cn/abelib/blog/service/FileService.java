package cn.abelib.blog.service;


import cn.abelib.blog.bean.File;

import java.util.List;

/**
 * 文件服务接口
 * Created by abel on 2017/11/7.
 */
public interface FileService {
    /**
     *  保存文件
     * @param file
     * @return
     */
    File addFile(File file);

    /**
     *  删除文件
     * @param id
     */
    void removeFile(String id);

    /**
     *  根据id查询文件
     * @param id
     * @return
     */
    File getFileById(String id);

    /**
     *  分页查询
     * @param pageIndex 当前页数， 默认是从第0页开始
     * @param pageSize 每页的具体条数
     * @return
     */
    List<File> listFileByPage(int pageIndex, int pageSize);
}
