package com.bjtu.ledger_management_system.controller.dto;

public class AddRecordEntity {
    private long strucid;
    private String value;

    public long getStrucid() {
        return strucid;
    }

    public void setStrucid(long strucid) {
        this.strucid = strucid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
