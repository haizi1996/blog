package com.hailin.blog.enumPackage;

/**
 * Demo class
 *
 * @author hailin
 * @date 2018/05/13 09:17
 */
public enum  RoleEnum {


    BLOGER(1 , "普通博主"),
    ADMIN(2 , "管理员");
    private int roleId ;

    private String desc;


    RoleEnum(int roleId, String desc) {
        this.roleId = roleId;
        this.desc = desc;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static RoleEnum parse(Integer roleId){
        switch (roleId){
            case 1:
                return BLOGER;
            case 2:
                return ADMIN;
                default:
                    return BLOGER;
        }
    }
}
