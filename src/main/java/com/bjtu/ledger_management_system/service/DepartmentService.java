package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.entity.Log;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
    void createDepartment(String superDid, Department newDepartment);
    void modifyDepartment(Department department);
    List<Department> getDepartmentList(String superDid,boolean isExpand);
    Page<Department> getDepartmentListPage(int pageNum, int pageSize);
    Department findByDname(String dname);
    Department findByDid(String did);

    /**
     * 模糊查找部门
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Department> getSpecificDep(String content, Integer pageNum, Integer pageSize);
}
