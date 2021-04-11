package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "department")
@Entity
public class Department {

    @Id
    @Column(name = "d_id")
    private String departmentId;
    @Column(name = "d_name")
    private String departmentName;
    @Column(name = "manager_id")
    private long managerId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "description")
    private String description;
    @Column(name = "d_tel")
    private String tel;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
