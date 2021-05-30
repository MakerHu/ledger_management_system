package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
import com.bjtu.ledger_management_system.dao.LogDao;
import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.DepartmentService;
import com.bjtu.ledger_management_system.service.LogService;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @Resource
    private LogService logService;

    @Resource
    private UserService userService;

    @PostMapping("/create")
    public Result createDepartment(HttpServletRequest request,@RequestParam String superDid, @RequestBody Department newDepartment){
        Department existDepartment = departmentService.findByDname(newDepartment.getDname());
        HttpSession session = request.getSession();
        UserMsgDTO dto= (UserMsgDTO) session.getAttribute("UserMsgDTO");
        Long uid = dto.getUid();
        if (existDepartment == null){
            departmentService.createDepartment(superDid,newDepartment);
            String content="创建了id为"+newDepartment.getDid()+"的部门";
           logService.addLog(uid,content);

            return Result.success();
        }else{
            String content="创建id为"+newDepartment.getDid()+"的部门失败(部门名已存在！)";
            logService.addLog(uid,content);

            return Result.error("4","部门名已存在！");
        }
    }

    @PostMapping("/modify")
    public Result modifyDepartment(HttpServletRequest request,@RequestBody Department department){
        Department existDepartment = departmentService.findByDid(department.getDid());
        HttpSession session = request.getSession();
        UserMsgDTO dto= (UserMsgDTO) session.getAttribute("UserMsgDTO");
        Long uid = dto.getUid();
        if (existDepartment != null){
            departmentService.modifyDepartment(department);

            String content="用户"+"修改了id为"+department.getDid()+"的部门";
            logService.addLog(uid,content);
            return Result.success();
        }else{
            String content="用户"+"修改id为"+department.getDid()+"的部门失败(此部门不存在！)";
            logService.addLog(uid,content);

            return Result.error("4","此部门不存在！");
        }
    }

    @GetMapping("/departmentlist")
    public Result<List<Department>> getDepartmentList(HttpServletRequest request,@RequestParam String superDid,@RequestParam boolean isExpand){
        Department existDepartment = departmentService.findByDid(superDid);
        HttpSession session = request.getSession();
        UserMsgDTO dto= (UserMsgDTO) session.getAttribute("UserMsgDTO");
        Long uid = dto.getUid();
        if (existDepartment != null){
            List<Department> requestList = departmentService.getDepartmentList(superDid,isExpand);

            String content="用户"+"获取id为"+superDid+"的部门列表";
            logService.addLog(uid,content);
            return Result.success(requestList);
        }else{
            String content="用户"+"获取id为"+superDid+"的部门列表失败（此部门不存在！）";
            logService.addLog(uid,content);
            return Result.error("4","此部门不存在！");
        }
    }

    @PostMapping("/switch")
    public Result<UserMsgDTO> switchDepartment(@RequestParam String did, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserMsgDTO userMsgDTO = (UserMsgDTO)session.getAttribute("userMsgDTO");
        userMsgDTO.setLastdid(did);
        userMsgDTO.setRoleListInLastDid(userService.getRolesInDepartment(did,userMsgDTO.getUid()));
        userMsgDTO.setRightListInLastDid(userService.getRightsInDepartment(userMsgDTO.getUid(),did));
        return Result.success(userMsgDTO);
    }
}
