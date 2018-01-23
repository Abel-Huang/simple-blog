package cn.abelib.blog.service;

import cn.abelib.blog.bean.Category;
import cn.abelib.blog.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by abel on 2017/11/22.
 *  Category的测试类
 *  测试通过
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @Test
    public void addCategoryTest(){
        User user = userService.getUserById(5L);
        Category category = new Category("ElasticSearch", user);
        Category saveCategory = categoryService.addCategory(category);
        System.err.println(saveCategory);
    }

    @Test
    public void removeCategoryTest(){
        categoryService.removeCategory(2L);
    }

    @Test
    public void getCategoryByIdTest(){
        Category category = categoryService.getCategoryById(1L);
        System.err.println(category.toString());
    }

    @Test
    public void listCategoriesTest(){
        User user = userService.getUserById(5L);
        List<Category> categories = categoryService.listCategories(user);
        for (Category category : categories){
            System.err.println(category);
        }
    }
}
