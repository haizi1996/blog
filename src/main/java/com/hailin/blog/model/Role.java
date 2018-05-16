package com.hailin.blog.model;

import java.util.Date;

public class Role {

    private int id ;
    private String name ; //'角色名',
    private int type ;  //'角色类型 0管理员:,1:普通,2:其它',
    private int status ; // '状态,1:正常状态 , 0代表冻结状态，2代表删除',
    private int seq ; // '权限在该权限模块下的排序,有小到大排序',
    private String remark ; // '备注',
    private String operator ; //'操作者',
    private Date createTime ; //'创建时间',
    private Date operateTime ;//'最后操作时间',
    private String operateIp ;//  '最后一次操作的ip'

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", seq=" + seq +
                ", remark='" + remark + '\'' +
                ", operator='" + operator + '\'' +
                ", createTime=" + createTime +
                ", operateTime=" + operateTime +
                ", operateIp=" + operateIp +
                '}';
    }
}
