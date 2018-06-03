package com.hailin.blog.constant;

/**
 * 点赞相关的常量
 */
public interface VoteConstant {


    enum Status{
        NORMAL(1,"正常未删除"),
        DELETED(0,"已删除");

        private Integer code;

        private String desc;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        Status(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}
