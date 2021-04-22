package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void add(User user);
    void update(User user);
    void del(long id);
    User findById(Long id);

    User findByEmail(String email);
    Result<List<Role>> getAllDepartRoles(String did, boolean isExpand);
    Page<User> findPage(Integer pageNum, Integer pageSize, String name);
    List<User> findUsersList(List<Long>userList);
    List<Long> findUidList(List<Role>roleList);
}
