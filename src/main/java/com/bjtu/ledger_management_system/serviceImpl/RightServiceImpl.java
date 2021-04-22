package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.*;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.RolesRights;
import com.bjtu.ledger_management_system.entity.UsersRoles;
import com.bjtu.ledger_management_system.service.RightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Resource
    RolesRightsDao rolesRightsDao;

    @Resource
    UsersRolesDao usersRolesDao;

    @Override
    public Page<Role> getAllDepartRoles(String did, boolean isExpand,Integer pageNum,Integer pageSize) {

        if (!departmentDao.findById(did).isPresent()) {
            return null;
        } else {
            if (!isExpand) {
                Sort sort = Sort.by(Sort.Direction.ASC,"roleid");
                PageRequest request= PageRequest.of(pageNum-1,pageSize,sort);
                Page<Role> rolepage= roleDao.findByDid(did,request);
                return rolepage;
            } else {
                Sort sort = Sort.by(Sort.Direction.ASC,"roleid");
                PageRequest request= PageRequest.of(pageNum-1,pageSize,sort);
                Page<Role> rolepage = roleDao.findByDidStartingWith(did,request);
                return rolepage;
            }
        }
    }

    @Override
    public boolean createRoleInDepart(Role role, List<Right> rightList) {
        List<Role> roleList = roleDao.findByDid(role.getDid());
        for (Role element : roleList) {
            if (element.getRolename().equals(role.getRolename())) {
                return false;
            }
        }
        roleDao.save(role);
        roleList = roleDao.findByDid(role.getDid());
        Long roleid;
        for (Role item : roleList) {
            if (item.getRolename().equals(role.getRolename())) {
                roleid = item.getRoleid();
                break;
            }
        }
        for (Right value : rightList) {
            Right right = new Right();
            right.setRightname(value.getRightname());
            right.setDescription(value.getDescription());
        }
        return true;
    }

    @Override
    public boolean modifyRoleInDepart(Role role, List<Right> rightList) {
        if (!roleDao.findById(role.getRoleid()).isPresent()) {
            return false;
        } else {
            Role newRole = new Role();
            newRole.setRoleid(role.getRoleid());
            newRole.setRolename(role.getRolename());
            newRole.setDid(role.getDid());
            newRole.setDescription(role.getDescription());
            roleDao.save(newRole);
            List<RolesRights> rolesRightsList = rolesRightsDao.findByRightid(role.getRoleid());
            /* 删除该角色所有权限 */
            for (RolesRights value : rolesRightsList){
                rolesRightsDao.delete(value);
            }
            /* 将新权限重新写入 */
            for(Right value:rightList){
                RolesRights newRolesRights=new RolesRights();
                newRolesRights.setRightid(value.getRightid());
                newRolesRights.setRoleid(role.getRoleid());
                rolesRightsDao.save(newRolesRights);
            }
            return true;
        }
    }

    @Override
    public void modifyOrAllotUserOneRole(Long uid, List<Long> roleidList) {
        List<UsersRoles> usersRoles = usersRolesDao.findByUid(uid);
        for(UsersRoles value:usersRoles){
            usersRolesDao.delete(value);
        }
        for(Long roleid:roleidList){
            UsersRoles newUsersRoles = new UsersRoles();
            newUsersRoles.setRoleid(roleid);
            newUsersRoles.setUid(uid);
            usersRolesDao.save(newUsersRoles);
        }
    }
}
