package com.bjtu.ledger_management_system.dao;


import com.bjtu.ledger_management_system.entity.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
//    Page<Template> getAll(Pageable pageable);


    /**
     * 模糊查询台账模板
     * @param tempid
     * @param creatorid
     * @param did
     * @param tempname
     * @param description
     * @param request
     * @return
     */
    Page<Template> findByTempidLikeOrCreatoridLikeOrDidContainingOrTempnameContainingOrDescriptionContaining(
            Long tempid,
            Long creatorid,
            String did,
            String tempname,
            String description,
            Pageable request
    );

    Page<Template> findByDidContainingOrTempnameContainingOrDescriptionContaining(
            String did,
            String tempname,
            String description,
            Pageable request
    );
}
