package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDao extends JpaRepository<Record,Long>{
    List<Record> findByLedgeridAndRowid(long ledgerid, long rowid);
    List<Record> findByStrucid(long id);
    List<Record> findByValue(String value);

}
