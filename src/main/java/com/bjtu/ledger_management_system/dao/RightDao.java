package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Right;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightDao extends JpaRepository<Right,Long>{
    Right findByRightid(long id);
    Right findByRightname(String name);
}
