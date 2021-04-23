package com.bjtu.ledger_management_system.controller;


import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.service.RightService;
import com.bjtu.ledger_management_system.serviceImpl.DTO.createRoleInDePartDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private static final long serialVersionUID = -1242493306307174690L;
    @Resource
    RightService rightService;

    @GetMapping
    public Result<Page<Role>> getAllDepartmentRoles(@RequestParam String did,
                                                    @RequestParam boolean isExpand,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize) {
        Page<Role> pagerole=rightService.getAllDepartRoles(did, isExpand, pageNum, pageSize);
        if(pagerole==null){
            return Result.error("5003","该部门不存在");
        }
        else {
            return Result.success(pagerole);
        }

    }

    @RequestMapping("/create")
    public Result<Role> createRoleInDepart(@RequestBody createRoleInDePartDTO dto) {

        Role role = dto.role;
        List<Right> rightList = dto.rightList;
        if (rightService.createRoleInDepart(role, rightList)) {
            return Result.success();
        } else {
            return Result.error("5001", "该角色在该部门已存在");
        }
    }

    @PostMapping("/modify")
    public Result modifyRoleInDepart(@RequestBody createRoleInDePartDTO dto) {
        Role role = dto.role;
        List<Right> rightList = dto.rightList;
        if (rightService.modifyRoleInDepart(role, rightList)) {
            return Result.success();
        } else {
            return Result.error("5002","该角色或该部门不存在");
        }
    }

    @PostMapping("/distribute")
    public Result modifyOrAllotUserOneRole(@RequestParam Long uid, @RequestBody List<Long> roleidList) {
        rightService.modifyOrAllotUserOneRole(uid, roleidList);
        return Result.success();
    }

}
