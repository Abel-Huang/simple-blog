package cn.abelib.blog.controller;

import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.pojo.Category;
import cn.abelib.blog.pojo.User;
import cn.abelib.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by abel on 2017/11/24.
 */
@RestController
@RequestMapping("/portal/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     *  获取分类列表
     * @return
     */
    @GetMapping("/list")
    public Response<List<Category>> listCategories(HttpSession session){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return categoryService.listCategories(user.getId());
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  获取分类列表
     * @return
     */
    @GetMapping("/list_user")
    public Response<List<Category>> listCategories(Long userId){
        return categoryService.listCategories(userId);
    }

    @PostMapping("/add")
    public Response createCategory(HttpSession session, String content){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return categoryService.addCategory(user.getId(), content);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response delete(HttpSession session, @PathVariable("id") Long id){
        User user = (User) session.getAttribute(StatusConstant.CURRENT_USER);
        if (user != null){
            return   categoryService.removeCategory(user.getId(), id);
        }
        return Response.failed(StatusConstant.USER_NOT_LOGIN);
    }

    /**
     *  通过id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response getCategoryById(@PathVariable("id") Long id){
        return   categoryService.getCategoryById(id);
    }
}
