package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.LogDao;
import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.LogService;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
//@CrossOrigin
//@RequestMapping("/")
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder encoding;

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            User user = userService.findByEmail(email);
            if (user != null){

//                if (user.getPassword().equals(password)){
                if (encoding.matches(password,user.getPassword())){

                    //设置session值
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                    session.setAttribute("msg","测试session");
                    System.out.println("set session: "+session.getAttribute("msg"));

                    // 判断用户是否不是新用户，新用户没有部门
                    if (!userService.isNewUser(user.getUid())){
                        String lastdid = user.getLastdid();
                        if (lastdid == null || lastdid.length()==0){    //判断lastdid是否为空，若为空说明用户是在拥有角色后第一次登录
                            // 选择用户的所属的其中一个部门作为本次进入的部门
                            lastdid = userService.getUserDepartments(user.getUid()).get(0).getDid();
                            user.setLastdid(lastdid);
                            userService.update(user);
                        }
                    }

                    //消除返回前端的收能过户数据中的重要信息
                    user.setPassword("");

                    Long uid=new Long("1");
                    String content="邮箱为"+email+"的用户登录成功";
                    logService.addLog(uid,content);

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
