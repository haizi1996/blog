package com.hailin.blog.dto;

public class Response <T>{

    //1代表成功返回
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

    public void setData(T data) {
        this.data = data;
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
}
