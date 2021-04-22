package com.bjtu.ledger_management_system.dao;


import com.bjtu.ledger_management_system.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
    List<Role> findByDid(String did);
    List<Role> findByDidStartingWith(String did);
    Page<Role> findByDid(String did, Pageable request);
    Page<Role> findByDidStartingWith(String did, Pageable pageable);


}
