package com.hailin.blog.constant;

/**
 * 排序枚举
 */
public enum  SortType {

    NONE(null , "无"),

    NEW("new" , "最新排序"),
    HOT("hot" , "最热排序");



    private String key;

    private String desc;

    SortType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static SortType parseToSortType(String order){
        switch (order){
        case "new":
            return NEW;
        case "hot":
            return HOT;
            default:
                return NONE;
        }
    }
}
