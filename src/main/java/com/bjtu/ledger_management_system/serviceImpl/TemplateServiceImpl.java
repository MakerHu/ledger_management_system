package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.controller.dto.CreateTemplateDTO;
import com.bjtu.ledger_management_system.controller.dto.TableHeadDTO;
import com.bjtu.ledger_management_system.dao.TemplateDao;
import com.bjtu.ledger_management_system.dao.TemplateRelationDao;
import com.bjtu.ledger_management_system.dao.TemplateStructureContentDao;
import com.bjtu.ledger_management_system.entity.Template;
import com.bjtu.ledger_management_system.entity.TemplateRelation;
import com.bjtu.ledger_management_system.entity.TemplateStructureContent;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.TemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

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

//        if(superid != -1){
//            TemplateRelation templateRelation = new TemplateRelation();
//            templateRelation.setTempid(tempid);
//            templateRelation.setSuperid(superid);
//            templateRelation.setSubid(thisStructId);
//            templateRelationDao.save(templateRelation);
            templateStructureContent.setSuperid(superid);
//        }
        if(superid == -1){
            TemplateStructureContent templateStructureContent2 = new TemplateStructureContent();
            templateStructureContent2.setTempid(tempid);
            templateStructureContent2.setStrucid(strucid);
            templateStructureContent2.setContent("序号");
            templateStructureContent2.setSuperid(0);
            templateStructureContentDao.save(templateStructureContent2);
            strucid++;
        }

        if(tableHeadDTO.getLabel()!=null){
            templateStructureContent.setContent(tableHeadDTO.getLabel());
        }
        templateStructureContentDao.save(templateStructureContent);

        if(tableHeadDTO.getChildren() != null &&tableHeadDTO.getChildren().size() > 0){
            for (TableHeadDTO th : tableHeadDTO.getChildren()){
                saveTableHead(tempid, th, thisStructId);
            }
        }



    }

    @Override
    public void add(Template newTemplate, TableHeadDTO tableHeadDTO) {
        templateDao.save(newTemplate);
        strucid = 0;
        saveTableHead(newTemplate.getTempid(), tableHeadDTO, -1);
    }

    /**
     * 获取模板表头及模板基本信息
     * @param tempid
     * @return
     */
    @Override
    public CreateTemplateDTO getTemplate(long tempid) {
        CreateTemplateDTO createTemplateDTO = new CreateTemplateDTO();
        Template template = findByTempId(tempid);
        createTemplateDTO.setNewTemplate(template);

        TableHeadDTO tableHeadDTO = new TableHeadDTO();
        tableHeadDTO.setLabel(template.getTempname());
        tableHeadDTO.setProp("0");

        List<TemplateStructureContent> tempStructConList = templateStructureContentDao.findByTempid(tempid);
        Queue<Long> superIdQueue =  new LinkedList<Long>();
        Queue<TableHeadDTO> tableHeadQueue =  new LinkedList<TableHeadDTO>();

        //向队列中加入根节点0
        superIdQueue.offer((long)0);
        tableHeadQueue.offer(tableHeadDTO);

        while(superIdQueue.size() > 0 ){
            long parentId = superIdQueue.poll();
            TableHeadDTO superTableHead = tableHeadQueue.poll();
            for(TemplateStructureContent tsc : tempStructConList){
                if(tsc.getSuperid() == parentId){
                    TableHeadDTO subTableHead = new TableHeadDTO();
                    subTableHead.setLabel(tsc.getContent());
                    subTableHead.setProp(Long.toString(tsc.getStrucid()));
                    superTableHead.getChildren().add(subTableHead);
                    tableHeadQueue.offer(subTableHead); //向队列中添加子节点
                    superIdQueue.offer(tsc.getStrucid());
                }
            }
        }

        createTemplateDTO.setTableHead(tableHeadDTO);

        return createTemplateDTO;
    }

    @Override
    public Template findByTempId(long tempid) {
        return templateDao.findById(tempid).orElse(null);
    }

    @Override
    public Page<Template> getTemplateList(int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum-1,pageSize);
        Page<Template> templatePage = templateDao.findAll(pageable);
        return templatePage;
    }


    /**
     * 模糊查找台账模板
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Template> getSpecificTemp(String content, Integer pageNum, Integer pageSize) {
        Page<Template> temppage = null;
        Sort sort = Sort.by(Sort.Direction.ASC, "tempid");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        String str = "%"+content+"%";
        temppage=templateDao.findTeplate(
                str,
                str,
                str,
                str,
                str,
                request
        );
        return temppage;
    }


}
