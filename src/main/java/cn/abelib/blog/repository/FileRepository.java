package cn.abelib.blog.repository;


import cn.abelib.blog.bean.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 文件
 * Created by abel on 2017/11/7.
 */
public interface FileRepository extends MongoRepository<File, String>{

}
