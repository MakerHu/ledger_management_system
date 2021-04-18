package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.TemplateStructureContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateStructureContentDao extends JpaRepository<TemplateStructureContent, Long> {
    List<TemplateStructureContent> findByTempid(long tempid);
    TemplateStructureContent findByTempidAndStrucid(long tempid,long strucid);
}
