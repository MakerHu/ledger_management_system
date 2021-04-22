package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;


}
