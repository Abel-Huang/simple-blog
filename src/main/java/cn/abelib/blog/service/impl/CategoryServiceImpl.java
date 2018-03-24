package cn.abelib.blog.service.impl;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.exception.GlobalException;
import cn.abelib.blog.common.result.Response;
import cn.abelib.blog.pojo.Category;
import org.apache.commons.collections4.CollectionUtils;
import cn.abelib.blog.repository.CategoryRepository;
import cn.abelib.blog.service.CategoryService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

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
    public Response<Category> addCategory(Long userId, String content) {
        if (StringUtils.isNotBlank(content)){
            throw new GlobalException(StatusConstant.PRAM_BIND_ERROR);
        }
        Category category = new Category(content, userId);
        // 判断是否重复
        List<Category> list = categoryRepository.findByUserIdAndContent(category.getUserId(), category.getContent());
        if (list !=null && list.size() > 0){
            throw new GlobalException(StatusConstant.CATEGORY_AL_EXISTS);
        }
        Category saveCategory = categoryRepository.save(category);
        if (saveCategory == null){
            throw new GlobalException(StatusConstant.GENERAL_SERVER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, saveCategory);
    }

    @Transactional
    @Override
    public Response removeCategory(Long userId, Long id) {
        Integer resultCount = categoryRepository.deleteByIdEqualsAndUserIdEquals(userId, id);
        if (resultCount != 1){
            throw new GlobalException(StatusConstant.CATEGORY_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    @Override
    public Response<Category> getCategoryById(Long id) {
        Category category = categoryRepository.findOne(id);
        if (category == null){
            throw new GlobalException(StatusConstant.CATEGORY_NOT_EXISTS);
        }
        return  Response.success(StatusConstant.GENERAL_SUCCESS, category);
    }

    @Override
    public Response<List<Category>> listCategories(Long userId) {
        List<Category> categories = Lists.newArrayList();
        categories = categoryRepository.findByUserId(userId);
        if (categories == null || CollectionUtils.isEmpty(categories)){
            throw new GlobalException(StatusConstant.CATEGORY_NOT_EXISTS);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS, categories);
    }
}
