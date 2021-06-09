package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.controller.dto.TableHeadDTO;
import com.bjtu.ledger_management_system.entity.Template;

public interface TemplateService {
    void add(Template newTemplate, TableHeadDTO tableHead);
}
