package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
//@RequestMapping("/")
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder encoding;

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request){
        try{
            User user = userService.findByEmail(email);
            if (user != null){

//                if (user.getPassword().equals(password)){
                if (encoding.matches(password,user.getPassword())){
                    //消除返回前端的收能过户数据中的重要信息
                    user.setPassword("");
                    //设置session值
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                    session.setAttribute("msg","测试session");
                    System.out.println("set session: "+session.getAttribute("msg"));
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
