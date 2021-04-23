package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DepartmentDao extends JpaRepository<Department,String>  {
    Department findByDname(String name);
    List<Department> findByDidStartingWith(String did);

    /**
     * 查询所有以did为父部门的下一级子部门
     * 其中length为did的长度+2
     * @param did
     * @param length
     * @return
     */
    @Query(value = "select * from department where did like ?1% and length(did)=?2 ",nativeQuery = true)
    List<Department> findByDidStartWithAndLength(String did, int length);
    List<Department> findByDmanager(long manager);
    List<Department> findByCreatetime(Date createtime);

}
