package com.hailin.blog.dao.es;

import com.hailin.BlogApplicationTests;
import com.hailin.blog.model.EsBlog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * author:hailin
 * Date:2018/5/30
 * Time:9:06
 */
public class EsBlogRepositoryTest extends BlogApplicationTests {

    @Resource
    private EsBlogRepository esBlogRepository;
    private static final String EMPTY_KEYWORD = "";
    @Before
    public  void initRepositoryData(){

        // 清空所有
        esBlogRepository.deleteAll();

        esBlogRepository.save(new EsBlog("1" , 1l ,"老卫跟你谈谈安装 Elasticsearch",
                "关于如何来安装 Elasticsearch，这个请看我的博客 https://waylau.com","请看我的博客 https://waylau.com","请看我的博客 https://waylau.com"));
        esBlogRepository.save(new EsBlog("2" , 2l ,"老卫跟你谈谈 Elasticsearch 的几个用法",
                "关于如何来用 Elasticsearch，还是得看我的博客 https://waylau.com，妹","请看我的博客 https://waylau.com","请看我的博客 https://waylau.com"));  // 关键字"妹"
        esBlogRepository.save(new EsBlog("3" , 3l,"老卫和你一起学 Elasticsearch",
                "如何来学习 Elasticsearch，最终看我的博客 https://waylau.com，酒","请看我的博客 https://waylau.com","请看我的博客 https://waylau.com")); // 关键字"酒"

        esBlogRepository.save(new EsBlog("4" , 4l ,"03-05 用大白话聊聊分布式系统，酒",
                "一提起“分布式系统”，大家的第一感觉就是好高大上啊，深不可测","请看我的博客 https://waylau.com","请看我的博客 https://waylau.com"));
        esBlogRepository.save(new EsBlog("5", 5l ,"02-19 Thymeleaf 3 引入了新的解析系统",
                "如果你的代码使用了 HTML5 的标准，而Thymeleaf 版本来停留在 2.x ，那么如果没有把闭合","请看我的博客 https://waylau.com","请看我的博客 https://waylau.com"));
        esBlogRepository.save(new EsBlog("6" ,6l,"02-19 使用 GFM Eclipse 插件时，不在项目里面生成 HTML 文件",
                "GFM 是 GitHub Flavored Markdown Viewer 的简称，是一款对 GitHub 友好的 Markdown 编辑器 。","请看我的博客 https://waylau.com","请看我的博客 https://waylau.com"));
    }

    @Test
    public void findByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining() {
        Pageable pageable = PageRequest.of(0,20);
        String  keyword = EMPTY_KEYWORD;
        
        Page<EsBlog> page = esBlogRepository.findAll( pageable);
        logger.info(page.toString());
//        assertThat(page.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void findByBlogId() {
        EsBlog esBlog = esBlogRepository.findByBlogId(1l);
        Iterable<EsBlog> esBlogs = esBlogRepository.findAll();
        logger.info(esBlog.toString());
    }

    @After
    public void clearAllData(){
        esBlogRepository.deleteAll();
    }
}