package com.hailin.blog.utils;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
*   
*  
* author:hailin 
* Date:2018/5/31  
* Time:16:59  
 * @Description
 * @Author 
 * @Date    
 * @Version
 */  

public class PageUtil {

    public static<T> PageInfo<T> pageTransforPageInfo(Page<T> page , Pageable pageable){
        PageInfo<T> pageInfo = null;
        if(page != null){
            pageInfo = PageInfo.of(page.getContent());
            pageInfo.setTotal(page.getTotalElements());
            pageInfo.setIsLastPage(page.isLast());
            pageInfo.setIsFirstPage(page.isFirst());
            pageInfo.setHasNextPage(page.hasNext());
            pageInfo.setHasPreviousPage(page.hasPrevious());
            pageInfo.setPageNum(page.getNumber());
            pageInfo.setPageSize(pageable.getPageSize());
            pageInfo.setPages(page.getTotalPages());
        }
        return pageInfo;
    }

}
