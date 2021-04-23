package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRolesDao extends JpaRepository<UsersRoles, Long> {
    List<UsersRoles> findByUid(long uid);
    List<UsersRoles> findByRoleid(long roleid);
}
