package com.bjtu.ledger_management_system.dao;


import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
    List<Role> findByDid(String did);
    Role findByRoleidAndDid(long roleid,String did);
    List<Role> findByDidStartingWith(String did);
    Page<Role> findByDid(String did, Pageable request);
    Page<Role> findByDidStartingWith(String did, Pageable pageable);

    /**
     * 模糊查询
     * @param roleid
     * @param rolename
     * @param did
     * @param description
     * @param request
     * @return
     */
//    Page<Role> findByRoleidLikeOrRolenameContainingOrDidContainingOrDescriptionContaining(
//            Long roleid,
//            String rolename,
//            String did,
//            String description,
//            Pageable request
//    );
//
//    Page<Role> findByRolenameContainingOrDidContainingOrDescriptionContaining(
//            String rolename,
//            String did,
//            String description,
//            Pageable request
//    );


    @Query(value = "select * from role where roleid LIKE ?1 OR rolename LIKE ?2 OR did LIKE ?3 OR description LIKE ?4",nativeQuery = true)
    Page<Role> findRole(
            String roleid,
            String rolename,
            String did,
            String description,
            Pageable request
    );
}
