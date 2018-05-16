package com.hailin.blog.dto;

/**
 * 返回一些只有一个值的数据
 *
 * @author hailin
 * @date 2018/05/13 15:31
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
