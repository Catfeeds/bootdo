package com.bootdo.blog.service;

import com.bootdo.blog.domain.ArticleCustom;
import com.bootdo.blog.domain.Pager;
import com.bootdo.blog.domain.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author Do
 * @package com.bootdo.blog.service
 * @name TagService
 * @date 2017/4/13
 * @time 18:55
 */
public interface TagService {
    /**
     * 获取当前tag下的文章列表
     * @param pager
     * @param tagId
     * @return
     */
    List<ArticleCustom> loadArticleByTag(Pager pager, Integer tagId);

    int getTagCount();

    Tag getTagById(Integer id);

    List<Tag> loadTagList(Map<String, Object> params);

    int saveTag(Tag tag);

    boolean checkExist(Tag tag);

    int updateTag(Tag tag);

    void initPage(Pager pager);

    List<Tag> getTagList();

    /**
     * 初始化分页
     * @param pager
     * @param tagId
     */
    void ArticleTagPage(Pager pager, int tagId);
    int deleteTag(Integer id);
    int batchRemove(Long[] ids);
    int count(Map<String, Object> params);
}
