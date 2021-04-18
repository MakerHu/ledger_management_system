package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.TemplateRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRelationDao extends JpaRepository<TemplateRelation, Long> {
    List<TemplateRelation> findByTempid(long tempId);
    List<TemplateRelation> findByTempidAndSuperid(long tempid,long superid);
}
