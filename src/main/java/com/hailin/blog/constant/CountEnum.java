package com.hailin.blog.constant;

/**
 * 统计相关的枚举
 * @author:hailin
 * Date:2018/5/30
 * Time:6:48
 */
public enum CountEnum {

    READ("read" ,"阅读量" ),
    COMMENT("comment" , "评论量"),
    VOTE("vote","点赞量");
    private String countType;

    private String desc;

    CountEnum(String countType, String desc) {
        this.countType = countType;
        this.desc = desc;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
