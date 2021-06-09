package com.bjtu.ledger_management_system.controller.dto;

import com.bjtu.ledger_management_system.entity.Template;

public class CreateTemplateDTO {
    private Template newTemplate;
    private TableHeadDTO tableHead;

    public Template getNewTemplate() {
        return newTemplate;
    }

    public void setNewTemplate(Template newTemplate) {
        this.newTemplate = newTemplate;
    }

    public TableHeadDTO getTableHead() {
        return tableHead;
    }

    public void setTableHead(TableHeadDTO tableHead) {
        this.tableHead = tableHead;
    }
}
