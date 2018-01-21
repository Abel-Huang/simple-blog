package cn.abelib.blog.controller;

import cn.abelib.blog.domain.Category;
import cn.abelib.blog.domain.User;
import cn.abelib.blog.service.CategoryService;
import cn.abelib.blog.service.UserService;
import cn.abelib.blog.util.ConstraintViolationExceptionHandler;
import cn.abelib.blog.util.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by abel on 2017/11/24.
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    /**
     *  获取分类列表
     * @return
     */
    @GetMapping
    public Response listCategories(@RequestParam(value = "username")String username){
        User user = userService.getUserByUsername(username);
        List<Category> categories = categoryService.listCategorys(user);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(categories, meta);
    }

    @PostMapping("/add")
    public Response createCategory(@RequestBody CategoryVO categoryVO){
        String username = categoryVO.getUsername();
        Category category = categoryVO.getCategory();

        User user = userService.getUserByUsername(username);

        Response response;
        Meta meta;
        try {
            category.setUser(user);
            categoryService.addCategory(category);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }catch (ConstraintViolationException e){
            meta = new Meta(HttpConstant.REQUEST_ERR, HttpConstant.REQUEST_ERR_STR);
            response = new Response(meta, ConstraintViolationExceptionHandler.getMessage(e));
        }catch (Exception ex){
            meta = new Meta(HttpConstant.SERVER_ERR, ex.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Response response;
        Meta meta;
        try {
           categoryService.removeCategory(id);
            meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
            response = new Response(meta);
        }catch (Exception e){
            meta = new Meta(HttpConstant.SERVER_ERR, e.getMessage());
            response = new Response(meta);
        }
        return response;
    }

    /**
     *  通过id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response getCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        Meta meta = new Meta(HttpConstant.RESPONSE_OK, HttpConstant.RESPONSE_OK_STR);
        return ResponseUtil.validator(category, meta);
    }
}
