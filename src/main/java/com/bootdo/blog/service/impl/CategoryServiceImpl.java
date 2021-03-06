package com.bootdo.blog.service.impl;


import com.bootdo.blog.dao.ArticleMapper;
import com.bootdo.blog.dao.CategoryMapper;
import com.bootdo.blog.service.CategoryService;
import com.bootdo.blog.domain.ArticleCustom;
import com.bootdo.blog.domain.Category;
import com.bootdo.blog.domain.CategoryCustom;
import com.bootdo.blog.domain.Pager;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.shop.domain.TGoodsClassDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by GeneratorFx on 2017-04-11.
*/
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleMapper articleMapper;



    @Override
    public List<ArticleCustom> loadArticleByCategory(Pager pager, Integer categoryId) {
        pager.getStart();
        return articleMapper.loadArticleByCategory(pager,categoryId);
    }

    @Override
    public List<CategoryCustom> initCategoryList() {
        return categoryMapper.initCategoryList();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public List<Category> loadCategory(Pager pager, String categoryName) {
        pager.setStart(pager.getStart());
        return categoryMapper.loadCategory(pager,categoryName);
    }

    @Override
    public boolean checkExist(Category category) {
        int count = categoryMapper.checkExist(category);
        if (count > 0){
            return true;
        }
        return false;
    }

    @Override
    public int saveCategory(Category category) {
        return  categoryMapper.saveCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
      return   categoryMapper.updateCategory(category);
    }

    @Override
    public void initPage(Pager pager) {
        int count = categoryMapper.initPage(pager);
        pager.setTotalCount(count);
    }

    @Override
    public List<Category> getCategoryList(Map<String, Object> params) {
        return categoryMapper.getCategoryList(params);
    }

    @Override
    public  int count(Map<String, Object> params) {
        return categoryMapper.count(params);
    }


    @Override
    public void ArticleCatePage(Pager pager, int categoryId) {
        int count = categoryMapper.ArticleCatePage(categoryId);
        pager.setTotalCount(count);
    }

    @Override
    public List<ArticleCustom> loadArticleByArchive(String createTime, Pager pager) {
        pager.getStart();
        return articleMapper.loadArticleByArchive(pager,createTime);
    }

    @Override
    public int getArticleCountByCategoryId(Integer categoryId) {
        return categoryMapper.ArticleCatePage(categoryId);
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        categoryMapper.deleteCategoryById(categoryId);
        articleMapper.updateCategoryId(categoryId);
        return true;
    }
    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteCategory(id);
    }

    @Override
    public int batchRemove(Long[] ids){
        return  categoryMapper.batchRemove(ids);
    }

    public int updateState(Integer id, int status){
        return   categoryMapper.updateState(id,status);
    }
}
