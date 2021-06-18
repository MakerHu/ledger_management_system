package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.LogDao;
import com.bjtu.ledger_management_system.dao.UserDao;
import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;
    @Resource
    private UserDao userDao;

    /**
     * 添加日志
     *
     * @param uid     操作人的id
     * @param content 操作内容
     */
    @Override
    public void addLog(Long uid, String content) {

        User user = userDao.findByUid(uid);

        Log log = new Log();
        log.setOperatorid(uid);
        Date date = new Date();
        log.setTime(date);
        log.setContent(content);
        log.setOperatorname(user.getUname());
        logDao.save(log);

    }

    /**
     * 获取所有日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Log> getAllLog(Integer pageNum, Integer pageSize) {
        Page<Log> logpage = null;
        Sort sort = Sort.by(Sort.Direction.DESC, "logid");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        logpage = logDao.findAll(request);
        return logpage;
    }

    /**
     * 根据模糊查找获取特定的值
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Log> getSpecificLog(String content, Integer pageNum, Integer pageSize) {
        Page<Log> logpage = null;
        Sort sort = Sort.by(Sort.Direction.ASC, "logid");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        String str = "%"+content+"%";
        logpage = logDao.findLog(
                str,
                str,
                str,
                str,
                request
        );
        return logpage;
    }

    @Override
    public Page<Log> getSpecificLogByOperatorId(Long operatorId, Integer pageNum, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "logid");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        return logDao.findByOperatorid(operatorId, request);
    }
}
