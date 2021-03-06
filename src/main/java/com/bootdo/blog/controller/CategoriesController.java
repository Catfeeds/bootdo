package com.bootdo.blog.controller;

import com.bootdo.blog.domain.Category;
import com.bootdo.blog.service.CategoryService;
import com.bootdo.blog.service.PartnerService;
import com.bootdo.blog.domain.ArticleCustom;
import com.bootdo.blog.domain.Pager;
import com.bootdo.blog.domain.Partner;
import com.bootdo.common.domain.Tree;
import com.bootdo.shop.domain.TGoodsClassDO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Controller
@RequestMapping("/blog/categories")
public class CategoriesController {

    @Resource
    CategoryService categoryService;
    @Resource
    PartnerService  partnerService;

    /**
     * 获取某个标签的分页文章
     * @param model
     * @param pager
     * @param categoryId
     * @return
     */
    @RequestMapping("/load/{categoryId}")
    public String loadArticleByCategory(Model model, Pager pager, @PathVariable Integer categoryId){
        List<ArticleCustom> articleList = categoryService.loadArticleByCategory(pager,categoryId);
        if (!articleList.isEmpty()){
            model.addAttribute("articleList",articleList);
            model.addAttribute("pager",pager);
            model.addAttribute("categoryName",articleList.get(0).getCategoryName());
        }
        return "blog/part/categorySummary";
    }
    /**
     * 根据时间获取文章分页信息
     * @param pager 分页对象
     * @param createTime 时间
     * @return
     */
    @RequestMapping("/createTime/load/{createTime}")
    public String loadCreateTimePager(Model model, Pager pager, @PathVariable String createTime){
        List<ArticleCustom> articleList= categoryService.loadArticleByArchive(createTime,pager);
        if (!articleList.isEmpty()){
            model.addAttribute("articleList",articleList);
            model.addAttribute("pager",pager);
            model.addAttribute("categoryName",articleList.get(0).getCategoryName());
        }
        return "blog/part/categorySummary";
    }
    /**
     * 跳转到分类的页面 暂时停用
     * @param model
     * @param categoryId
     * @return
     */
    @Deprecated
    @RequestMapping("/details/{categoryId}")
    public String categoryPage(Model model, @PathVariable Integer categoryId){
        Map<String, Object> params=new HashedMap();
        params.put("status","1");
        List<Partner> partnerList = partnerService.findAll(params);
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("categoryId",categoryId);
        return "blog/category";
    }



}
