package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
//@RequestMapping("/")
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String email, @RequestParam String password){
        try{
            User user = userService.findByEmail(email);
            if (user != null){
                if (user.getPassword().equals(password)){
                    //消除返回前端的收能过户数据中的重要信息
                    user.setPassword("");
                    return Result.success(user);
                }else {
                    return Result.error("1", "用户名或密码错误！");
                }
            }else{
                return Result.error("2", "用户名或密码错误！");
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
