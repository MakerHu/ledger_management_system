package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface UserService {
    void add(User user);
    void update(User user);
    void del(long id);
    User findById(Long id);
    User findByEmail(String email);
    Page<User> findPage(Integer pageNum, Integer pageSize, String name);
}
