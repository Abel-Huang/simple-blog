package cn.abelib.blog.repository;


import cn.abelib.blog.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 文件
 * Created by abel on 2018/1/7.
 */
public interface FileRepository extends MongoRepository<File, String>{

}
