package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.Log;
import org.springframework.data.domain.Page;

public interface LogService {
    /**
     * 添加日志
     * @param uid 操作人的id
     * @param content 操作内容
     */
    void addLog(Long uid,String content);

    /**
     * 获取所有日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Log> getAllLog(Integer pageNum, Integer pageSize);

    /**
     * 根据模糊查找获取特定的值
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Log> getSpecificLog(String content,Integer pageNum, Integer pageSize);
}
