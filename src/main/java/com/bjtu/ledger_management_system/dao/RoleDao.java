package com.bjtu.ledger_management_system.dao;


import com.bjtu.ledger_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
    List<Role> findByDid(String did);


}
