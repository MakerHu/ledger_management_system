package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.dao.LogDao;
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
//        List<Department> departmentList = departmentDao.findByDidStartingWith(superDid);
        List<Department> departmentList = departmentDao.findNextLevelDepartment(superDid, superDid.length()+2);
        int departmentCount = departmentList.size();
        String newDid = superDid + "." + (departmentCount+1);
        newDepartment.setDid(newDid);
        departmentDao.save(newDepartment);
    }

    /**
     * 修改部门信息
     * @param department
     */
    @Override
    public void modifyDepartment(Department department) {
        departmentDao.save(department);
    }

    /**
     * 获取某部门下的所有部门
     * @param superDid
     * @param isExpand 为true时向下拓展否则只传回下一级子部门
     * @return
     */
    @Override
    public List<Department> getDepartmentList(String superDid, boolean isExpand) {
        if(isExpand){
            return departmentDao.findTotalSubDepartment(superDid,superDid.length());
        }else{
            return departmentDao.findNextLevelDepartment(superDid,superDid.length()+2);
        }
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
