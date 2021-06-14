package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.entity.Ledger;
import com.bjtu.ledger_management_system.entity.Record;
import org.json.JSONArray;

import java.util.List;

public interface LedgerService {
    Ledger createLedger(Ledger ledger);
    void insertRecord(long ledgerid, long rowid, List<Record> recordList);
    void delRecord(long ledgerid, long rowid);
    void updateRecord(long ledgerid, List<Record> recordList);

    JSONArray getRecordListByPage(long ledgerid, int pageNum, int pageSize);
}
