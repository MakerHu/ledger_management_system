package com.bjtu.ledger_management_system.controller.dto;

import com.bjtu.ledger_management_system.entity.Record;
import com.bjtu.ledger_management_system.entity.Template;

import java.util.List;

public class CreateTemplateDTO {
    private Template newTemplate;
    private TableHeadDTO tableHead;
    private List<AddRecordEntity> addRecordEntity;

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

    public List<AddRecordEntity> getAddRecordEntity() {
        return addRecordEntity;
    }

    public void setAddRecordEntity(List<AddRecordEntity> addRecordEntity) {
        this.addRecordEntity = addRecordEntity;
    }
}
