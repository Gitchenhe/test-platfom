package com.chenhe.service.dto;

import java.util.Date;

/**
 * @author chenhe
 * @Date 2018-04-18 9:13
 * @desc 测试spring 事务管理使用的表
 **/
public class ExceptionEntity {
    private int id;
    private int version;
    private String remark;
    private String requestNo;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
