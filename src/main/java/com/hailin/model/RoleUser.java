package com.hailin.model;

import com.hailin.enumPackage.RoleEnum;

import java.util.Date;

public class RoleUser {


    private Integer id ; // ,
    private Integer roleId ;// '角色ID',
    private Integer userId ; //  '用户Id',
    private String operator ; // '操作者',
    private Date createTime ; // '创建时间',
    private Date operateTime ; // '最后操作时间',
    private String operateIp ; // '最后一次操作的ip'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", operator='" + operator + '\'' +
                ", createTime=" + createTime +
                ", operateTime=" + operateTime +
                ", operateIp='" + operateIp + '\'' +
                '}';
    }

    /**
     * 创建一个普通博主角色对应关系
     * @return
     */
    public static RoleUser createBolgerRole(Integer userId){
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(userId);
        roleUser.setRoleId(RoleEnum.BLOGER.getRoleId());
        return roleUser;

    }
}
