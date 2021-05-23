package com.bjtu.ledger_management_system.controller;


import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    //获取所有日志
    @GetMapping
    public Result<Page<Log>> getAllLog(Integer pageNum, Integer pageSize){
        Long uid=new Long("1");
        String content="查看了所有日志";
        logService.addLog(uid,content);
        return Result.success(logService.getAllLog(pageNum,pageSize));
    }

    //模糊搜索
    @PostMapping
    public Result<Page<Log>> getSpecificLogs(String content,Integer pageNum, Integer pageSize){
        Long uid=new Long("1");
        String contents="以字段“"+content+"”模糊查找了日志";
        logService.addLog(uid,contents);
        return Result.success(logService.getSpecificLog(content,pageNum,pageSize));
    }
}
