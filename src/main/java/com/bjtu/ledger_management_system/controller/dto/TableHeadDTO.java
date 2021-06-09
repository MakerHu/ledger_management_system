package com.bjtu.ledger_management_system.controller.dto;

import java.util.List;

public class TableHeadDTO {
    private String label;
    private String prop;
    private List<TableHeadDTO> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public List<TableHeadDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TableHeadDTO> children) {
        this.children = children;
    }
}
