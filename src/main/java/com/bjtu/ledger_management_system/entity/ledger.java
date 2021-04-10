package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ledger")
@Entity

public class ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ledger_id;

    private String ledger_name;
    private String d_id;
    private long creator_id;
    private Date create_time;
    private long temp_id;
    private String record_tablename;
    private String description;

    public long getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(long ledger_id) {
        this.ledger_id = ledger_id;
    }

    public String getLedger_name() {
        return ledger_name;
    }

    public void setLedger_name(String ledger_name) {
        this.ledger_name = ledger_name;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public long getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(long temp_id) {
        this.temp_id = temp_id;
    }

    public String getRecord_tablename() {
        return record_tablename;
    }

    public void setRecord_tablename(String record_tablename) {
        this.record_tablename = record_tablename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
