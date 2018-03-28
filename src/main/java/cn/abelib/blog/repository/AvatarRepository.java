package cn.abelib.blog.repository;


import cn.abelib.blog.pojo.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abel on 2017/11/7.
 *
 */

public interface AvatarRepository  extends JpaRepository<Avatar, Long> {

}
