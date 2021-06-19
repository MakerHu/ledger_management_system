package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.DistributeDTO;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
import com.bjtu.ledger_management_system.entity.Right;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.LogService;
import com.bjtu.ledger_management_system.service.RightService;
import com.bjtu.ledger_management_system.controller.dto.CreateRoleInDepartDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
                                                    @RequestParam Integer pageSize,
                                                    HttpServletRequest request) {
        Page<Role> pagerole=rightService.getAllDepartRoles(did, isExpand, pageNum, pageSize);
        HttpSession session = request.getSession();
        UserMsgDTO dto= (UserMsgDTO) session.getAttribute("userMsgDTO");
        Long uid = dto.getUid();
        if(pagerole==null){
            String content="查看部门下的角色失败（该部门不存在）";
            logService.addLog(uid,content);
            return Result.error("5003","该部门不存在");
        }
        else {
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
    public Result<Role> createRoleInDepart(@RequestBody CreateRoleInDepartDTO dto,HttpServletRequest request) {

        Role role = dto.role;
        List<Right> rightList = dto.rightList;
        HttpSession session = request.getSession();
        UserMsgDTO userMsgDTO= (UserMsgDTO) session.getAttribute("userMsgDTO");
        Long uid = userMsgDTO.getUid();
        if (rightService.createRoleInDepart(role, rightList)) {
            String content="在部门"+role.getDid()+"创建了角色"+role.getRoleid();
            logService.addLog(uid,content);
            return Result.success();
        } else {
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
    public Result modifyRoleInDepart(@RequestBody CreateRoleInDepartDTO dto,HttpServletRequest request) {
        Role role = dto.role;
        List<Right> rightList = dto.rightList;
        HttpSession session = request.getSession();
        UserMsgDTO userMsgDTO= (UserMsgDTO) session.getAttribute("userMsgDTO");
        Long uid = userMsgDTO.getUid();
        if (rightService.modifyRoleInDepart(role, rightList)) {
            String content="在部门"+role.getDid()+"修改了角色"+role.getRoleid();
            logService.addLog(uid,content);
            return Result.success();
        } else {
            String content="修改角色失败（该角色或该部门不存在）";
            logService.addLog(uid,content);
            return Result.error("5002","该角色或该部门不存在");
        }
    }

    /**
     * 分配角色
     * @example POST 请求体 {
     *     "uid": 5,
     *     "roleidList": [ 23, 27 ]
     * }
     * @return Result
     */
    @PostMapping("/distribute")
    public Result modifyOrAllotUserOneRole(HttpServletRequest request, @RequestBody DistributeDTO distributeDTO) {
        long uid = distributeDTO.getUid();
        List<Long> roleidList = distributeDTO.getRoleidList();
        rightService.modifyOrAllotUserOneRole(uid, roleidList);
        HttpSession session = request.getSession();
        UserMsgDTO userMsgDTO= (UserMsgDTO) session.getAttribute("userMsgDTO");
        Long userid=userMsgDTO.getUid();
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

    /**
     * 模糊查找用户
     * @param request
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    public Result<Page<Role>> queryRoles(HttpServletRequest request,
                                         @RequestParam String content,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        return Result.success(rightService.getSpecificRole(content,pageNum,pageSize));
    }

    @GetMapping("/rightsofrole")
    public Result<List<Right>> getRightsOfRole(HttpServletRequest request,
                                               @RequestParam long roleid){
        return Result.success(rightService.getRightsOfRole(roleid));
    }

}
