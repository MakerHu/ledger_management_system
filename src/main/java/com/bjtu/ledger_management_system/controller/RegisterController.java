package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private BCryptPasswordEncoder encoding;

    @Resource
    private UserService userService;

    @PostMapping("/single")
    public Result<User> createSingleUser(@RequestBody User newUser){
        try{
            User existUser = userService.findByEmail(newUser.getEmail());
            if (existUser == null){
                newUser.setPassword(encoding.encode(newUser.getPassword()));
                userService.add(newUser);
                return Result.success();
            }else{
                return Result.error("3", "邮箱已被使用！");
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/batch")
    public Result<List<User>> createBatchUser(@RequestBody List<User> newUserList){
        List<User> errorList = new ArrayList<>();
        for (User newUser : newUserList) {
            User existUser = userService.findByEmail(newUser.getEmail());

            if (existUser == null){
                newUser.setPassword(encoding.encode(newUser.getPassword()));
                userService.add(newUser);

            }else{
                errorList.add(newUser);
            }
        }
        if(errorList.size()==0){
            return Result.success();
        }else{
            return Result.success(errorList,"存在邮箱重复！");
        }
    }

}
