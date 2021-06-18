package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.entity.Ledger;
import com.bjtu.ledger_management_system.entity.Record;
import org.json.JSONArray;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LedgerService {
    Ledger createLedger(Ledger ledger);
    void insertRecord(long ledgerid, long rowid, List<Record> recordList);
    void delRecord(long ledgerid, long rowid);
    void updateRecord(long ledgerid,long rowid, List<Record> recordList);

    JSONArray getRecordListByPage(long ledgerid, int pageNum, int pageSize);

    Page<Ledger> getLedgerList(String did, int pageNum, int pageSize);

    /**
     * 模糊查找台账
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Ledger> getSpecificLedger(String content, Integer pageNum, Integer pageSize);
}
