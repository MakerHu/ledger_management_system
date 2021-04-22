package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    /**
     * 在数据库中创建部门
     * @param superDid
     * @param newDepartment
     */
    @Override
    public void createDepartment(String superDid, Department newDepartment) {
        List<Department> departmentList = departmentDao.findByDidStartingWith(superDid);
        int departmentCount = departmentList.size();
        String newDid = superDid + "." + (departmentCount);
        newDepartment.setDid(newDid);
        departmentDao.save(newDepartment);
    }

    @Override
    public void modifyDepartment(Department department) {
        departmentDao.save(department);
    }

    @Override
    public Department findByDname(String dname) {
        return  departmentDao.findByDname(dname);
    }

    @Override
    public Department findByDid(String did) {
        return departmentDao.findById(did).orElse(null);
    }


}
