package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RightService {

    /**
     *
     * @param did 部门编号
     * @param isExpand  是否向下拓展（是否将该部门的子部门角色也获取出来）
     * @param pageNum 页码编号
     * @param pageSize  页码大小
     * @return Role的分页对象
     *
     */
    Page<Role> getAllDepartRoles(String did, boolean isExpand,Integer pageNum,Integer pageSize);


    /**
     * 3.2.在某部门下创建角色
     * @param role   Role对象
     * @param rightList  权限数组
     * @return  是否成功创建
     */
    boolean createRoleInDepart(Role role, List<Right> rightList);

    /**
     * 3.3.修改某部门某角色信息
     * @param role  Role对象
     * @param rightList  权限数组
     * @return 是否成功修改
     */
    boolean modifyRoleInDepart(Role role,List<Right> rightList);


    /**
     * 3.4.用户角色分配与用户角色修改
     * @param uid  用户编号
     * @param roleidList  角色编号数组
     * @return 无
     */
    void modifyOrAllotUserOneRole(Long uid,List<Long> roleidList);


    /**
     *
     * @param did 部门编号
     * @param isExpand  是否显示子部门
     * @return Role列表
     */
    List<Role> getAllDepartRolesList(String did, boolean isExpand);


}
