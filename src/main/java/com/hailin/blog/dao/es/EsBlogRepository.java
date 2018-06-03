package com.hailin.blog.dao.es;

import com.hailin.blog.model.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author:hailin
 * Date:2018/5/30
 * Time:8:43
 */
public interface EsBlogRepository extends ElasticsearchCrudRepository<EsBlog,String> {


    /**
     * 根据 Bloges 的id 查询 EsBlog
     * @param blogId
     * @return
     */
    EsBlog findByBlogId(Long blogId);
    /**
     * 模糊查询(去重)
     * @param title
     * @param summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findByTitleOrSummaryOrContentOrTags(
            String title,String summary,String content,String tags,Pageable pageable);

}
