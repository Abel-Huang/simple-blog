package cn.abelib.blog.service.impl;


import cn.abelib.blog.domain.File;
import cn.abelib.blog.repository.FileRepository;
import cn.abelib.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 *  文件服务实现类
 * Created by abel on 2018/1/7.
 */
@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileRepository fileRepository;

    @Override
    public File addFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeFile(String id) {
        fileRepository.delete(id);
    }

    @Override
    public File getFileById(String id) {
        return fileRepository.findOne(id);
    }

    @Override
    public List<File> listFileByPage(int pageIndex, int pageSize) {
        Page<File> page;
        Sort sort = new Sort(Sort.Direction.DESC, "uploadDate");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        page = fileRepository.findAll(pageable);
        return page.getContent();
    }
}
