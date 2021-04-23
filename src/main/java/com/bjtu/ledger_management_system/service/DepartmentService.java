package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.Department;

import java.util.List;

public interface DepartmentService {
    void createDepartment(String superDid, Department newDepartment);
    void modifyDepartment(Department department);
    List<Department> getDepartmentList(String superDid,boolean isExpand);
    Department findByDname(String dname);
    Department findByDid(String did);
}
