package com.hailin.blog.constant;

public  interface CatalogConstant {

       enum  Status {
         NORMAL(1,"未删除") , DELETE(0,"已删除");

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
