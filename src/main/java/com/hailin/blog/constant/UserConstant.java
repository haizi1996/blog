package com.hailin.blog.constant;

/**
 * blog实体相关的常量
 */
public interface UserConstant {

    enum  Status {
        NORMAL(1,"未删除") , DELETE(2,"已删除") , FROZEN(0 , "冻结");

        private int code;
        private String desc;

        private Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
