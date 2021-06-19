package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.LedgerDao;
import com.bjtu.ledger_management_system.dao.RecordDao;
import com.bjtu.ledger_management_system.dao.TemplateStructureContentDao;
import com.bjtu.ledger_management_system.entity.Department;
import com.bjtu.ledger_management_system.entity.Ledger;
import com.bjtu.ledger_management_system.entity.Record;
import com.bjtu.ledger_management_system.entity.TemplateStructureContent;
import com.bjtu.ledger_management_system.service.LedgerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Transactional
@Service
public class LedgerServiceImpl implements LedgerService {
    @Resource
    RecordDao recordDao;

    @Resource
    LedgerDao ledgerDao;

    @Resource
    TemplateStructureContentDao templateStructureContentDao;


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
                if(record.getStrucid() == 1){
                    record.setValue(String.valueOf(oldRowid+1));
                }

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
    public void updateRecord(long ledgerid,long rowid, List<Record> recordList) {
        List<Record> oldRecordList = recordDao.findByLedgeridAndRowid(ledgerid,rowid);
        for(Record rc : oldRecordList){
            for(Record rc2 : recordList){
                if(rc2.getStrucid() == rc.getStrucid()){
                    rc.setValue(rc2.getValue());
                    recordDao.save(rc);
                }
            }
        }
    }

    @Override
    public int getLedgerRecordNum(long ledgerid) {
        //计算台账一共有几列
        Ledger ledger = ledgerDao.findById(ledgerid).orElse(null);
        List<TemplateStructureContent> tempStructList = templateStructureContentDao.findByTempid(ledger.getTempid());
        List<Long> rootList = new ArrayList<>();
        for(TemplateStructureContent tsc : tempStructList){
            if(!rootList.contains(tsc.getSuperid())){
                rootList.add(tsc.getSuperid());
            }
        }
        int columnSize = tempStructList.size()-rootList.size()+1;
        int totalRecordNum = recordDao.findByLedgerid(ledgerid).size();
        int rowSize = totalRecordNum/columnSize;

        return rowSize;
    }

    @Override
    public JSONArray getRecordListByPage(long ledgerid, int pageNum, int pageSize) {
        long idFrom = (pageNum - 1) * pageSize;
        long idTo = idFrom + pageSize;

        Sort sort = Sort.by(Sort.Direction.ASC, "rowid");
        List<Record> recordList = recordDao.findByLedgeridAndRowidBetween(ledgerid, idFrom, idTo,sort);
//        return recordList;

        //计算台账一共有几列
        Ledger ledger = ledgerDao.findById(ledgerid).orElse(null);
        List<TemplateStructureContent> tempStructList = templateStructureContentDao.findByTempid(ledger.getTempid());
        List<Long> rootList = new ArrayList<>();
        for(TemplateStructureContent tsc : tempStructList){
            if(!rootList.contains(tsc.getSuperid())){
                rootList.add(tsc.getSuperid());
            }
        }

        int columnSize = tempStructList.size()-rootList.size()+1;
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

    @Override
    public Page<Ledger> getLedgerList(String did, int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum-1,pageSize);
        Page<Ledger> ledgerPage = ledgerDao.findByDid(did,pageable);
        return ledgerPage;
    }


    /**
     * 模糊查询台账
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Ledger> getSpecificLedger(String content, Integer pageNum, Integer pageSize) {
        Page<Ledger> ledgerpage = null;
        Sort sort = Sort.by(Sort.Direction.ASC, "ledgerid");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        String str = "%"+content+"%";
        ledgerpage=ledgerDao.findLedger(
                str,
                str,
                str,
                str,
                str,
                str,
                request
        );
        return ledgerpage;
    }
}
