package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LogDao extends JpaRepository<Log, Long> {

    Page<Log> findAll(Pageable request);

    List<Log> findByOperatorid(Long operatorId, Pageable request);

    Page<Log> findByLogidLikeOrContentContainingOrOperatoridLikeOrOperatornameContaining(Long logid,
                                                                             String content,
                                                                             Long operatorid,
                                                                             String operatorname,
                                                                             Pageable request);

    Page<Log> findByContentContainingOrOperatornameContaining(
            String content,
            String operatorname,
            Pageable request);
}
