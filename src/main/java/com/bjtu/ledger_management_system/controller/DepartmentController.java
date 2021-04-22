package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @PostMapping("/create")
    public Result createDepartment(@RequestParam String superDid, @RequestBody Department newDepartment){
        Department existDepartment = departmentService.findByDname(newDepartment.getDname());
        if (existDepartment == null){
            departmentService.createDepartment(superDid,newDepartment);
            return Result.success();
        }else{
            return Result.error("4","部门名已存在！");
        }
    }

    @PostMapping("/modify")
    public Result modifyDepartment(@RequestBody Department department){
        Department existDepartment = departmentService.findByDid(department.getDid());
        if (existDepartment != null){
            departmentService.modifyDepartment(department);
            return Result.success();
        }else{
            return Result.error("4","此部门不存在！");
        }
    }
}