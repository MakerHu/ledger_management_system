package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.LogService;
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

    @Resource
    private LogService logService;

    @PostMapping("/single")
    public Result<User> createSingleUser(@RequestBody User newUser){
        try{
            User existUser = userService.findByEmail(newUser.getEmail());
            if (existUser == null){
                newUser.setPassword(encoding.encode(newUser.getPassword()));
                userService.add(newUser);
                Long uid=new Long("1");
                String content="创建了账户"+newUser.getEmail();
                logService.addLog(uid,content);
                return Result.success();
            }else{
                Long uid=new Long("1");
                String content="创建账户"+newUser.getEmail()+"失败（邮箱已被使用！）";
                logService.addLog(uid,content);
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
            Long uid=new Long("1");
            String content="批量创建账户成功";
            logService.addLog(uid,content);
            return Result.success();
        }else{
            Long uid=new Long("1");
            String content="批量创建账户成功,但存在邮箱重复：";
            for(int i=0;i<errorList.size();i++){
                content=content+errorList.get(i);
                if(i!=errorList.size()-1){
                    content=content+",";
                }
            }
            logService.addLog(uid,content);
            return Result.success(errorList,"存在邮箱重复！");
        }
    }

}
