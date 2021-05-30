package com.bjtu.ledger_management_system.controller;


import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    //获取所有日志
    @GetMapping
    public Result<Page<Log>> getAllLog(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return Result.success(logService.getAllLog(pageNum,pageSize));
    }

    //模糊搜索
    @PostMapping
    public Result<Page<Log>> getSpecificLogs(@RequestParam String content,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return Result.success(logService.getSpecificLog(content,pageNum,pageSize));
    }

    //根据操作员搜索日志
    @PostMapping(path = "/{uid}")
    public Result<Page<Log>> getSpecificLogsByOperatorId(@PathVariable Long uid,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return Result.success(logService.getSpecificLogByOperatorId(uid, pageNum, pageSize));
    }
}
