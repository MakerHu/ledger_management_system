package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Log;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LogDao extends JpaRepository<Log, Long> {

    Page<Log> findAll(Pageable request);

    Page<Log> findByOperatorid(Long operatorId, Pageable request);

    Page<Log> findByLogidLikeOrContentContainingOrOperatoridLikeOrOperatornameContaining(Long logid,
                                                                             String content,
                                                                             Long operatorid,
                                                                             String operatorname,
                                                                             Pageable request);

    /**
     * 模糊搜索日志
     *
     */
    @Query(value = "select * from log where logid LIKE ?1 OR content LIKE ?2 OR operatorid LIKE ?3 OR operatorname LIKE ?4",nativeQuery = true)
    Page<Log> findLog(
            String logid,
            String content,
            String operatorid,
            String operatorname,
            Pageable request
    );
}
