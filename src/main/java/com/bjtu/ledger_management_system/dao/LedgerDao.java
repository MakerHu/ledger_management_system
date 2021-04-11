package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LedgerDao extends JpaRepository<Ledger, Long> {
    List<Ledger> findByLedgerName(String ledgerName);
    List<Ledger> findByDepartmentId(String departmentId);
    List<Ledger> findByCreatorId(long creatorId);
}
