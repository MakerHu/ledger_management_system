package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
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
//@RequestMapping("")
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder encoding;

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @PostMapping("/login")
    public Result<UserMsgDTO> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            User user = userService.findByEmail(email);
            if (user != null){

//                if (user.getPassword().equals(password)){
                if (encoding.matches(password,user.getPassword())){

                    // 判断用户是否不是新用户，新用户没有部门
                    boolean isNewUser = userService.isNewUser(user.getUid());
                    if (!isNewUser){
                        String lastdid = user.getLastdid();
                        if (lastdid == null || lastdid.length()==0){    //判断lastdid是否为空，若为空说明用户是在拥有角色后第一次登录
                            // 选择用户的所属的其中一个部门作为本次进入的部门
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            lastdid = userService.getUserDepartments(user.getUid()).get(0).getDid();
                            user.setLastdid(lastdid);
                            userService.update(user);
                        }
                    }

                    //消除返回前端的收能过户数据中的重要信息
                    UserMsgDTO userMsgDTO = new UserMsgDTO();
                    userMsgDTO.setUser(user);
                    if(!isNewUser){
                        userMsgDTO.setRoleListInLastDid(userService.getRolesInDepartment(user.getLastdid(),user.getUid()));
                        userMsgDTO.setRightListInLastDid(userService.getRightsInDepartment(user.getUid(),user.getLastdid()));
                        userMsgDTO.setDepartmentList(userService.getUserDepartments(user.getUid()));
                    }


                    //设置session值
                    HttpSession session = request.getSession();
                    session.setAttribute("userMsgDTO",userMsgDTO);
                    session.setAttribute("msg","测试session");
                    System.out.println("set session: "+session.getAttribute("msg"));

                    Long uid=user.getUid();
                    String content="邮箱为"+email+"的用户登录成功";
                    logService.addLog(uid,content);
                    return Result.success(userMsgDTO);
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

    /**
     * 测试session，可删
     * @param request
     */
    @GetMapping("/test")
    public Result<String> test(HttpServletRequest request){
        System.out.println("test");
        HttpSession session = request.getSession();
        String msg = session.getAttribute("msg").toString();
        System.out.println(msg);

        return Result.success(msg);
    }

    @PostMapping("/signout")
    public Result logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("logout: "+session.getAttribute("msg"));
        session.removeAttribute("userMsgDTO");
        return Result.success();
    }

}
