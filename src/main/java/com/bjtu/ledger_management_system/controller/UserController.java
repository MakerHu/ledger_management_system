package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private DepartmentDao departmentDao;

    @PostMapping("/modify")
    public Result<User> modify(@RequestBody User modifyUser){
        try{
            User existUser = userService.findByEmail(modifyUser.getEmail());
            if (existUser == null){
                userService.update(modifyUser);
                return Result.success();
            }else{
                return Result.error("2", "邮箱重复！");
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/userlist")
    public Result<List<User>> getUserList(@RequestParam String did, @RequestParam boolean isExpand){
        try{
            if (!departmentDao.findById(did).isPresent()) {
                return Result.error("5001", "无此部门");
            }
            Result<List<Role>> roleList = userService.getAllDepartRoles(did,isExpand);
            List<Long>  uidList = userService.findUidList(roleList.getData());
            return Result.success(userService.findUsersList(uidList));

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
