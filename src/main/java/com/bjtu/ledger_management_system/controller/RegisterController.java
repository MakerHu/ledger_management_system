package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RegisterController {
    @Resource
    private UserService userService;

}
