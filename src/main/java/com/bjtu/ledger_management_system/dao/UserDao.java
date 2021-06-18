package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUname(String name);
    User findByUid(Long uid);
    User findByEmail(String email);
    Page<User> findByUid(Long uid, Pageable pageable);

    /**
     * 模糊查找用户
     * @param uid
     * @param uname
     * @param email
     * @param lastdid
     * @param request
     * @return
     */
//    Page<User> findByUidLikeOrUnameContainingOrEmailContainingOrLastdidContaining(
//            Long uid,
//            String uname,
//            String email,
//            String lastdid,
//            Pageable request
//    );
//
//    Page<User> findByUnameContainingOrGenderOrEmailContainingOrLastdidContaining(
//            String uname,
//            boolean gender,
//            String email,
//            String lastdid,
//            Pageable request
//    );


    @Query(value = "select * from user where uname LIKE ?1 OR email LIKE ?2 OR lastdid LIKE ?3 OR uid LIKE ?4 OR gender = ?5",nativeQuery = true)
    Page<User> findUserswithgender(
            String uname,
            String email,
            String lastdid,
            String uid,
            int gender,
            Pageable request
    );

    @Query(value = "select * from user where uname LIKE ?1 OR email LIKE ?2 OR lastdid LIKE ?3 OR uid LIKE ?4",nativeQuery = true)
    Page<User> findUserswithoutgender(
            String uname,
            String email,
            String lastdid,
            String uid,
            Pageable request
    );
}
