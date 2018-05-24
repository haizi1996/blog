package com.hailin.blog.dto;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class Response <T>{

    //1代表成功返回
    //0代表失败
    // -1代表参数错误
    private int code ;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public T getData() {
        return data;
    }

    public Response setData(T data) {
        this.data = data;
        return this;
    }

    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static<T> Response successResponse (T t){
        return new Response(1 , t);
    }

    public static <T> Response errorResponse(String erroeMessage){
        return new Response(0 , erroeMessage);
    }

    /**
     * 错误参数的响应数据
     * @param erroeMessage
     * @param <T>
     * @return
     */
    public static <T> Response errorParamterResponse( String erroeMessage , T t){
        return new Response(-1 , erroeMessage).setData(t);
    }
}
