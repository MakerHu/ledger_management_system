package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ledger")
@Entity
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private long ledgerId;
    @Column(name = "ledger_name")
    private String ledgerName;
    @Column(name = "d_id")
    private String departmentId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "creator_id")
    private long creatorId;
    @Column(name = "temp_id")
    private long tempId;
    @Column(name = "record_tablename")
    private String recordTableName;
    @Column(name = "description")
    private String description;

    public long getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(long ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getTempId() {
        return tempId;
    }

    public void setTempId(long tempId) {
        this.tempId = tempId;
    }

    public String getRecordTableName() {
        return recordTableName;
    }

    public void setRecordTableName(String recordTableName) {
        this.recordTableName = recordTableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
