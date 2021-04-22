package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.Department;

public interface DepartmentService {
    void createDepartment(String superDid, Department newDepartment);
    void modifyDepartment(Department department);
    Department findByDname(String dname);
    Department findByDid(String did);
}
