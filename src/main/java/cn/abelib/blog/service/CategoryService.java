package cn.abelib.blog.service;

import cn.abelib.blog.domain.Category;
import cn.abelib.blog.domain.User;

import java.util.List;

/**
 * Created by abel on 2018/1/21.
 */
public interface CategoryService {
    /**
     *  新建分类
     * @param category
     * @return
     */
    Category addCategory(Category category);

    /**
     *  删除分类
     * @param id
     */
    void removeCategory(Long id);

    /**
     *  通过ID查询
     * @param id
     * @return
     */
    Category getCategoryById(Long id);

    /**
     *  获取Category列表
     * @param user
     * @return
     */
    List<Category> listCategorys(User user);
}
