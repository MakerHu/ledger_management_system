package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Ledger;
import com.bjtu.ledger_management_system.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LedgerDao extends JpaRepository<Ledger, Long> {
    List<Ledger> findByLedgername(String ledgerName);
    Page<Ledger> findByDid(String did, Pageable pageable);
    List<Ledger> findByCreatorid(long creatorId);

    void deleteLedgerByLedgerid(long ledgerid);


    /**
     * 模糊查询台账列表
     * @param ledgerid
     * @param ledgername
     * @param did
     * @param creatorid
     * @param tempid
     * @param description
     * @param request
     * @return
     */
    @Query(value = "select * from ledger where ledgerid LIKE ?1 OR ledgername LIKE ?2 OR did LIKE ?3 OR creatorid LIKE ?4 OR tempid LIKE ?5 OR description LIKE ?6",nativeQuery = true)
    Page<Ledger> findLedger(
            String ledgerid,
            String ledgername,
            String did,
            String creatorid,
            String tempid,
            String description,
            Pageable request
    );


}
