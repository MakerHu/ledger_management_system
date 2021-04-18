package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends JpaRepository<Record,Long>{
    Record findByLedgerid(long id);
    Record findByStrucid(long id);
    Record findByRowid(long id);
    Record findByValue(String value);
}
