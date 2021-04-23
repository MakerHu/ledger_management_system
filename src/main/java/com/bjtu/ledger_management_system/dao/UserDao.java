package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUname(String name);
    User findByUid(Long uid);
    User findByEmail(String email);
    Page<User> findByUid(Long uid, Pageable pageable);
}
