package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.dao.RightDao;
import com.bjtu.ledger_management_system.dao.RoleDao;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.service.RightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RightServiceImpl implements RightService {

    @Resource
    DepartmentDao departmentDao;

    @Resource
    RightDao rightDao;

    @Resource
    RoleDao roleDao;

    @Override
    public Result<List<Role>> getAllDepartRoles(String did,boolean isExpand) {
        if (departmentDao.findById(did).isEmpty()) {
            return Result.error("5001", "无此部门");
        } else {
            if(!isExpand){
                List<Role> roleList = roleDao.findByDid(did);
                return Result.success(roleList);
            }
            else{
                List<Role> roleList = roleDao.findByStartingWithDid(did);
                return Result.success(roleList);
            }
        }





    }

    @Override
    public Result<Role> createRoleInDepart(Role role, List<Right> rightList) {
        List<Role> roleList = roleDao.findByDid(role.getDid());
        for(int i=0;i<roleList.size();i++){
            if(roleList.get(i).getRolename().equals(role.getRolename())){
                return Result.error("5002","此部门已有该角色");
            }
        }

        roleDao.save(role);
        roleList = roleDao.findByDid(role.getDid());
        Long roleid;
        for(int i=0;i<roleList.size();i++){
            if(roleList.get(i).getRolename().equals(role.getRolename())){
                roleid=roleList.get(i).getRoleid();
                break;
            }
        }
        for(int i=0;i<rightList.size();i++){
            Right right = new Right();
            right.setRightname(rightList.get(i).getRightname());
            right.setDescription(rightList.get(i).getDescription());
        }


        return Result.success();
    }

    @Override
    public Result modifyRoleInDepart(Role role, List<Right> rightList) {
        return null;
    }

    @Override
    public Result modifyOrAllotUserOneRole(Long uid, Long roleid) {
        return null;
    }
}
