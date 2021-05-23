package com.bjtu.ledger_management_system.controller;


import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.service.LogService;
import com.bjtu.ledger_management_system.service.RightService;
import com.bjtu.ledger_management_system.controller.dto.CreateRoleInDepartDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private static final long serialVersionUID = -1242493306307174690L;
    @Resource
    private RightService rightService;

    @Resource
    private LogService logService;

    /**
     * 查看部门下的所有角色（Roles）
     * @param did 部门id
     * @param isExpand 是否拓展
     * @param pageNum 总页数
     * @param pageSize 每页的大小
     * @return
     */
    @GetMapping
    public Result<Page<Role>> getAllDepartmentRoles(@RequestParam String did,
                                                    @RequestParam boolean isExpand,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize) {
        Page<Role> pagerole=rightService.getAllDepartRoles(did, isExpand, pageNum, pageSize);
        if(pagerole==null){
            Long uid=new Long("1");
            String content="查看部门下的角色失败（该部门不存在）";
            logService.addLog(uid,content);
            return Result.error("5003","该部门不存在");
        }
        else {
            Long uid=new Long("1");
            String content="查看了部门"+did+"的角色";
            logService.addLog(uid,content);
            return Result.success(pagerole);
        }

    }

    /**
     * 创建角色
     * @param dto Role和List<Right>的集合
     * @return
     */
    @RequestMapping("/create")
    public Result<Role> createRoleInDepart(@RequestBody CreateRoleInDepartDTO dto) {

        Role role = dto.role;
        List<Right> rightList = dto.rightList;
        if (rightService.createRoleInDepart(role, rightList)) {
            Long uid=new Long("1");
            String content="在部门"+role.getDid()+"创建了角色"+role.getRoleid();
            logService.addLog(uid,content);
            return Result.success();
        } else {
            Long uid=new Long("1");
            String content="创建角色失败（该角色在该部门已存在）";
            logService.addLog(uid,content);
            return Result.error("5001", "该角色在该部门已存在");
        }
    }


    /**
     * 修改角色
     * @param dto Role和List<Right>的集合
     * @return
     */
    @PostMapping("/modify")
    public Result modifyRoleInDepart(@RequestBody CreateRoleInDepartDTO dto) {
        Role role = dto.role;
        List<Right> rightList = dto.rightList;
        if (rightService.modifyRoleInDepart(role, rightList)) {
            Long uid=new Long("1");
            String content="在部门"+role.getDid()+"修改了角色"+role.getRoleid();
            logService.addLog(uid,content);
            return Result.success();
        } else {
            Long uid=new Long("1");
            String content="修改角色失败（该角色或该部门不存在）";
            logService.addLog(uid,content);
            return Result.error("5002","该角色或该部门不存在");
        }
    }

    /**
     * 分配角色
     * @param uid 需要分配的用户id
     * @param roleidList 角色列表
     * @return
     */
    @PostMapping("/distribute")
    public Result modifyOrAllotUserOneRole(@RequestParam(name="uid") Long uid, @RequestParam("roleidList") List<Long> roleidList) {
        rightService.modifyOrAllotUserOneRole(uid, roleidList);
        Long userid=new Long("1");
        StringBuilder content= new StringBuilder("为用户" + uid + "分配了角色：");
        for(int i=0;i<roleidList.size();i++){
            content.append(roleidList.get(i));
            if(i!=roleidList.size()-1){
                content.append(",");
            }
        }
        logService.addLog(userid, content.toString());
        return Result.success();
    }

}
