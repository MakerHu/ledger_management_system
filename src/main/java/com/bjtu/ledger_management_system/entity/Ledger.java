package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ledger")
@Entity
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ledgerid;

    private String ledgername;

    private String did;

    private Date createtime;

    private long creatorid;

    private long tempid;

    private String description;

    public long getLedgerid() {
        return ledgerid;
    }

    public void setLedgerid(long ledgerid) {
        this.ledgerid = ledgerid;
    }

    public String getLedgername() {
        return ledgername;
    }

    public void setLedgername(String ledgername) {
        this.ledgername = ledgername;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(long creatorid) {
        this.creatorid = creatorid;
    }

    public long getTempid() {
        return tempid;
    }

    public void setTempid(long tempid) {
        this.tempid = tempid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
