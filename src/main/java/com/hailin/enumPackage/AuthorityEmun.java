package com.hailin.enumPackage;

public interface AuthorityEmun {

     enum AuthorityID{


        ADMIN_USER_AUTHORITY_ID(1,"管理员"),
        ROLE_USER_AUTHORITY_ID(2 , "普通用户");

        AuthorityID(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private int code;

        private String desc;

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


     enum State{

        DELETED(0,"已删除"),
        USED(1 , "在使用");
        private int code;

        private String desc;


        State(int code, String desc) {
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
