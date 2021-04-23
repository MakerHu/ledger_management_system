package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Result<Page<User>> getUserList(@RequestParam String did, @RequestParam boolean isExpand,
                                          @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        try{
            if (!departmentDao.findById(did).isPresent()) {
                return Result.error("5001", "无此部门");
            }
            if( pageNum <= 0){
                return Result.error("5003", "pageNum必须大于0");
            }
            List<Role> roleList = userService.getAllDepartRoles(did,isExpand);
            List<Long>  uidList = userService.findUidList(roleList);
            List<User> userList = userService.findUsersList(uidList);
            if(userService.listConvertToPage(userList,pageNum,pageSize)==null){
                return Result.error("5002", "当前pageNum无数据");
            }
            return Result.success(userService.listConvertToPage(userList,pageNum,pageSize));

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
