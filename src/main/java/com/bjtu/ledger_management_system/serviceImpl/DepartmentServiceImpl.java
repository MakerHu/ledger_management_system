package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.dao.LogDao;
import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

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

    /**
     * 获取所有部门列表（分页）
     * @return
     */
    @Override
    public Page<Department> getDepartmentListPage(int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum-1,pageSize);
        Page<Department> allDepartmentPage = departmentDao.selectAllDepartment(pageable);
        return allDepartmentPage;
    }

    @Override
    public Department findByDname(String dname) {
        return  departmentDao.findByDname(dname);
    }

    @Override
    public Department findByDid(String did) {
        return departmentDao.findById(did).orElse(null);
    }


    /**
     * 模糊查询部门
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Department> getSpecificDep(String content, Integer pageNum, Integer pageSize) {
        Page<Department> deppage = null;
        Sort sort = Sort.by(Sort.Direction.DESC, "did");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        Pattern pattern = Pattern.compile("[0-9]*");
        if(pattern.matcher(content).matches()){
            deppage = departmentDao.findByDidContainingOrDnameContainingOrDmanagerLikeOrDescriptionContainingOrTelContaining(
                    content,
                    content,
                    new Long(content),
                    content,
                    content,
                    request
            );
        }else {
            deppage=departmentDao.findByDidContainingOrDnameContainingOrDescriptionContainingOrTelContaining(
                    content,
                    content,
                    content,
                    content,
                    request
            );
        }
        return deppage;
    }


}
