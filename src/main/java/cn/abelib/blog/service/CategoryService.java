package cn.abelib.blog.service;

import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Category;
import cn.abelib.blog.pojo.User;

import java.util.List;

/**
 * Created by abel on 2018/1/21.
 */
public interface CategoryService {
    /**
     *   新建分类
     * @param userId
     * @param content
     * @return
     */
    Response<Category> addCategory(Long userId, String content);

    /**
     *  删除分类
     * @param userId
     * @param id
     * @return
     */
    Response removeCategory(Long userId, Long id);

    /**
     *  通过ID查询
     * @param id
     * @return
     */
    Response<Category> getCategoryById(Long id);

    /**
     *  获取Category列表
     * @param userId
     * @return
     */
    Response<List<Category>> listCategories(Long userId);
}
