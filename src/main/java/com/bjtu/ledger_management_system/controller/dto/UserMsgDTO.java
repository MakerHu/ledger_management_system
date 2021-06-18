package com.bjtu.ledger_management_system.controller.dto;

import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMsgDTO {
    private long uid;
    private String uname;
    private boolean gender;
    private String email;
    private Date birthday;
    private String lastdid;
    private List<Role> roleListInLastDid = new ArrayList<>();
    private List<Role> roleList = new ArrayList<>();
    private List<Right> rightListInLastDid = new ArrayList<>();
    private List<Department> departmentList = new ArrayList<>();    //用户所在的所有部门

    public UserMsgDTO(){
    }

    public void setUser(User user){
        this.uid=user.getUid();
        this.uname = user.getUname();
        this.gender = user.isGender();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.lastdid = user.getLastdid();
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLastdid() {
        return lastdid;
    }

    public void setLastdid(String lastdid) {
        this.lastdid = lastdid;
    }

    public List<Role> getRoleListInLastDid() {
        return roleListInLastDid;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void setRoleListInLastDid(List<Role> roleListInLastDid) {
        this.roleListInLastDid = roleListInLastDid;
    }

    public List<Right> getRightListInLastDid() {
        return rightListInLastDid;
    }

    public void setRightListInLastDid(List<Right> rightListInLastDid) {
        this.rightListInLastDid = rightListInLastDid;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
