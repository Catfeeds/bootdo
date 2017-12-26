package com.bootdo.blog.service.impl;


import com.bootdo.blog.dao.ArticleMapper;
import com.bootdo.blog.service.BlogArticleService;

import com.bootdo.blog.domain.Article;
import com.bootdo.blog.domain.ArticleCustom;
import com.bootdo.blog.domain.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Service
@Transactional
public class BlogArticleServiceImpl implements BlogArticleService {

    @Resource
    private ArticleMapper articleMapper;

//    @Resource
//    private BaiduTask baiduTask;

    @Override
    public List<ArticleCustom> articleList(Pager pager) {

        return articleMapper.getArticleList(pager);
    }

    @Override
    public Pager<Article> InitPager() {
        Pager pager = new Pager();
        int count = articleMapper.getArticleCount();
        pager.setTotalCount(count);
        return pager;
    }

    @Override
    public int getArticleCount() {
        return articleMapper.getArticleCount();
    }

    @Override
    public void InitPager(Pager pager) {
        int count = articleMapper.initPage(pager);
        pager.setTotalCount(count);
    }

    @Override
    public List<Article> loadArticle(Map<String, Object> param) {
        return articleMapper.loadArticle(param);
    }

    @Override
    public void updateStatue(Integer id, int status) {
        articleMapper.updateStatue(id,status);
    }

    @Override
    public void saveArticle(Article article, int[] tags) throws IOException {
        Integer id  = getRandomId();
        for (int i = 0; i < 50; i++) {
            int count = articleMapper.checkExist(id);
            if (count==0) break;
            else id = getRandomId();
        }
        article.setId(id);
        article.setCreateTime(new Date());
        article.setStatus(1);
        articleMapper.saveArticle(article);
        articleMapper.saveArticleTag(id,tags);
    //    baiduTask.pushOneArticle(String.valueOf(id));
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }


    @Override
    public void updateArticle(Article article, int[] tags) {
        article.setUpdateTime(new Date());
        articleMapper.updateArticle(article);
        articleMapper.deleteArticleTag(article.getId());
        articleMapper.saveArticleTag(article.getId(),tags);
    }

    @Override
    public int deleteArticle(Integer id) {
       return articleMapper.deleteArticle(id);
    }

    @Override
    public ArticleCustom getArticleCustomById(Integer articleId) {
        return articleMapper.getArticleCustomById(articleId);
    }
    @Override
    public ArticleCustom getAArticleCustomById(Integer articleId) {
        return articleMapper.getAArticleCustomById(articleId);
    }
    @Override
    public Article getLastArticle(Integer articleId) {
        return articleMapper.getLastArticle(articleId);
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        return articleMapper.getNextArticle(articleId);
    }

    @Override
    public void addArticleCount(Integer articleId) {
        articleMapper.addArticleCount(articleId);
    }

    @Override
    public List<ArticleCustom> popularArticle() {
        return articleMapper.popularArticle();
    }

    @Override
    public String[] getArticleId() {
        return articleMapper.getArticleId();
    }

    @Override
    public List<Article> getArticleListByKeywords(String keyword) {
        return articleMapper.getArticleListByKeywords(keyword);
    }

    @Override
    public List<Map> articleArchiveList() {
        return articleMapper.articleArchiveList();
    }

    private Integer getRandomId(){
        Calendar instance = Calendar.getInstance();
        int month = instance.MONTH;
        int dayOfMonth = instance.DAY_OF_MONTH;
        int random = new Random().nextInt(8999)+1000;
        StringBuilder append = new StringBuilder().append(month).append(dayOfMonth).append(random);

        return Integer.valueOf(append.toString());
    }
}
