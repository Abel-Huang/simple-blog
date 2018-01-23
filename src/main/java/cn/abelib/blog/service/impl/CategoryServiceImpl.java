package cn.abelib.blog.service.impl;

import cn.abelib.blog.bean.Category;
import cn.abelib.blog.bean.User;
import cn.abelib.blog.repository.CategoryRepository;
import cn.abelib.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by abel on 2017/11/21.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category addCategory(Category category) {
        // 判断是否重复
        List<Category> list = categoryRepository.findByUserAndName(category.getUser(), category.getName());
        if (list !=null && list.size() > 0){
            throw new IllegalArgumentException("该分类已经存在");
        }
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void removeCategory(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> listCategories(User user) {
        return categoryRepository.findByUser(user);
    }
}
