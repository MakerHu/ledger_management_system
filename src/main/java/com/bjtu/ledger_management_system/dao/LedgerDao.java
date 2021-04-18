package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LedgerDao extends JpaRepository<Ledger, Long> {
    List<Ledger> findByLedgername(String ledgerName);
    List<Ledger> findByDid(String departmentId);
    List<Ledger> findByCreatorid(long creatorId);
}
