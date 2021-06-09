package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.controller.dto.TableHeadDTO;
import com.bjtu.ledger_management_system.dao.TemplateDao;
import com.bjtu.ledger_management_system.dao.TemplateRelationDao;
import com.bjtu.ledger_management_system.dao.TemplateStructureContentDao;
import com.bjtu.ledger_management_system.entity.Template;
import com.bjtu.ledger_management_system.entity.TemplateRelation;
import com.bjtu.ledger_management_system.entity.TemplateStructureContent;
import com.bjtu.ledger_management_system.service.TemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TemplateServiceImpl implements TemplateService {
    private long strucid = 0;

    @Resource
    TemplateDao templateDao;

    @Resource
    TemplateRelationDao templateRelationDao;

    @Resource
    TemplateStructureContentDao templateStructureContentDao;

    /**
     * 通过递归将表头信息存入template_relation与template_structure_content数据库
     * @param tempid
     * @param tableHeadDTO
     * @param superid
     */
    public void saveTableHead(long tempid, TableHeadDTO tableHeadDTO, long superid){
        //为表头中每个字段创建对应实体对象
        long thisStructId = strucid;
        strucid++;
        TemplateStructureContent templateStructureContent = new TemplateStructureContent();
        templateStructureContent.setTempid(tempid);
        templateStructureContent.setStrucid(thisStructId);


        if(tableHeadDTO.getLabel()!=null){
            templateStructureContent.setContent(tableHeadDTO.getLabel());
        }
        templateStructureContentDao.save(templateStructureContent);

        if(tableHeadDTO.getChildren() != null &&tableHeadDTO.getChildren().size() > 0){
            for (TableHeadDTO th : tableHeadDTO.getChildren()){
                saveTableHead(tempid, th, thisStructId);
            }
        }

        if(superid != -1){
            TemplateRelation templateRelation = new TemplateRelation();
            templateRelation.setTempid(tempid);
            templateRelation.setSuperid(superid);
            templateRelation.setSubid(thisStructId);
            templateRelationDao.save(templateRelation);
        }

    }

    @Override
    public void add(Template newTemplate, TableHeadDTO tableHeadDTO) {
        templateDao.save(newTemplate);
        strucid = 0;
        saveTableHead(newTemplate.getTempid(), tableHeadDTO, -1);
    }


}
