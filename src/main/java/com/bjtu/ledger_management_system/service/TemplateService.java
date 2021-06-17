package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.controller.dto.CreateTemplateDTO;
import com.bjtu.ledger_management_system.controller.dto.TableHeadDTO;
import com.bjtu.ledger_management_system.entity.Template;
import org.springframework.data.domain.Page;

public interface TemplateService {
    void add(Template newTemplate, TableHeadDTO tableHead);
    CreateTemplateDTO getTemplate(long tempid);
    Template findByTempId(long tempid);
    Page<Template> getTemplateList(int pageNum, int PageSize);
}
