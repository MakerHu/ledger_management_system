package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;

import java.util.List;

public interface RightService {

    /**
     *
     * @param did  部门编号
     * @param isExpand  是否向下拓展（是否将该部门的子部门角色也获取出来）
     * @return
     */
    Result<List<Role>> getAllDepartRoles(String did,boolean isExpand);


    /**
     * 3.2.在某部门下创建角色
     * @param role   Role对象
     * @param rightList  权限数组
     * @return
     */
    Result<Role> createRoleInDepart(Role role, List<Right> rightList);

    /**
     * 3.3.修改某部门某角色信息
     * @param role  Role对象
     * @param rightList  权限数组
     * @return
     */
    Result modifyRoleInDepart(Role role,List<Right> rightList);


    /**
     * 3.4.用户角色分配与用户角色修改
     * @param uid  用户编号
     * @param roleid  角色编号
     * @return
     */
    Result modifyOrAllotUserOneRole(Long uid,Long roleid);

}
