package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "record")
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long ledgerid;

    private long strucid;

    private long rowid;

    private Date createtime;

    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLedgerid() {
        return ledgerid;
    }

    public void setLedgerid(long ledgerid) {
        this.ledgerid = ledgerid;
    }

    public long getStrucid() {
        return strucid;
    }

    public void setStrucid(long strucid) {
        this.strucid = strucid;
    }

    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
