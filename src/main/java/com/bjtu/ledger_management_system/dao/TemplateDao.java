package com.bjtu.ledger_management_system.dao;


import com.bjtu.ledger_management_system.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TemplateDao extends JpaRepository<Template,Long> {
    List<Template> findByCreatorid(Long creatorid);
    List<Template> findByDid(String did);
    Template findByTempname(String tempname);
    List<Template> findByCreatetime(Date createtime);

}
