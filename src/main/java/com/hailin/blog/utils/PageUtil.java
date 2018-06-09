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
            pageInfo.setPageNum(pageable.getPageNumber() + 1);
            pageInfo.setPageSize(pageable.getPageSize());
            pageInfo.setPages((int)(pageInfo.getTotal() + pageable.getPageSize() - 1)/pageable.getPageSize() );
            pageInfo.setIsLastPage(pageable.getPageNumber() + 1 != pageInfo.getPages());
            pageInfo.setIsFirstPage(pageable.getPageNumber() + 1 != 1);
            pageInfo.setHasNextPage(pageable.getPageNumber() + 1 != pageInfo.getPages());
            pageInfo.setHasPreviousPage(pageable.getPageNumber() + 1 != 1);
        }
        return pageInfo;
    }

}
