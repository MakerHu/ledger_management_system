package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.LedgerDao;
import com.bjtu.ledger_management_system.dao.RecordDao;
import com.bjtu.ledger_management_system.entity.Ledger;
import com.bjtu.ledger_management_system.entity.Record;
import com.bjtu.ledger_management_system.service.LedgerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class LedgerServiceImpl implements LedgerService {
    @Resource
    RecordDao recordDao;

    @Resource
    LedgerDao ledgerDao;


    @Override
    public Ledger createLedger(Ledger ledger) {
        ledgerDao.save(ledger);
        return ledger;
    }

    @Override
    public void insertRecord(long ledgerid, long rowid, List<Record> recordList) {

        if (rowid != -1) {
            List<Record> behindList = recordDao.findByLedgeridAndRowidGreaterThanEqual(ledgerid, rowid);
            for (Record record : behindList) {
                long oldRowid = record.getRowid();
                record.setRowid(oldRowid + 1);
                recordDao.save(record);
            }
        } else {
            int columnSize = recordDao.findByLedgeridAndRowid(ledgerid, 0).size();
            if (columnSize > 0) {
                rowid = recordDao.findByLedgerid(ledgerid).size() / columnSize;
            } else {
                rowid = 0;
            }
        }

        Record rowRecord = new Record();
        rowRecord.setStrucid(1);
        rowRecord.setValue(String.valueOf(rowid));
        rowRecord.setRowid(rowid);
        rowRecord.setLedgerid(ledgerid);
        recordDao.save(rowRecord);

        for (Record record2 : recordList) {
            record2.setRowid(rowid);
            record2.setLedgerid(ledgerid);
            recordDao.save(record2);
        }
    }

    @Override
    public void delRecord(long ledgerid, long rowid) {
        recordDao.deleteAllByLedgeridAndRowid(ledgerid, rowid);
        List<Record> behindList = recordDao.findByLedgeridAndRowidGreaterThanEqual(ledgerid, rowid);

        for (Record record : behindList) {
            long oldRowid = record.getRowid();
            record.setRowid(oldRowid - 1);
            recordDao.save(record);
        }
    }

    @Override
    public void updateRecord(long ledgerid, List<Record> recordList) {

    }

    @Override
    public JSONArray getRecordListByPage(long ledgerid, int pageNum, int pageSize) {
        long idFrom = (pageNum - 1) * pageSize;
        long idTo = idFrom + pageSize;
        List<Record> recordList = recordDao.findByLedgeridAndRowidBetween(ledgerid, idFrom, idTo);
//        return recordList;

        int columnSize = recordDao.findByLedgeridAndRowid(ledgerid, 0).size();
        int rowSize = recordList.size()/columnSize;
        JSONArray lineArray = new JSONArray();

        int index =0;
        for(int i=0;i<rowSize;i++){
            JSONObject lineObj = new JSONObject();
            for(int j=0;j<columnSize;j++){
               Record record = recordList.get(index);
               try{
                   lineObj.put(String.valueOf(record.getStrucid()),record.getValue());
               }catch(Exception e) {
                   e.printStackTrace();
               }
               index++;
            }
            lineArray.put(lineObj);
        }
        return lineArray;
    }
}
