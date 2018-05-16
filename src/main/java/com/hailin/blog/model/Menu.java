package com.hailin.blog.model;

import java.io.Serializable;

public class Menu implements Serializable{

    private int id;

    private String name ;

    private String url ;

    //优先级 越小靠前
    private int priority;

    private int status ;



    public Menu() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
