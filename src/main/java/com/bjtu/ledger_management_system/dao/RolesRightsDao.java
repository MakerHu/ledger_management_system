package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.RolesRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRightsDao extends JpaRepository<RolesRights,Long> {
    List<RolesRights> findByRoleid(Long roleid);
    List<RolesRights> findByRightid(Long rightid);
}
