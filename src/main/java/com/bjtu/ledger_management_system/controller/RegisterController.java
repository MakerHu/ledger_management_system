package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private BCryptPasswordEncoder encoding;

    @Resource
    private UserService userService;

    @PostMapping("/single")
    public Result<User> register(@RequestBody User newUser){
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

}
