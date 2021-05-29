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
     * @param subDidLength
     * @return
     */
    @Query(value = "select * from department where did like ?1% and length(did)=?2 ",nativeQuery = true)
    List<Department> findNextLevelDepartment(String did, int subDidLength);

    /**
     * 查询某部门下的所有子部门（整棵树，不包括他自己）
     * 其中superDidLength为此部门的部门编号长度
     * @param did
     * @param superDidLength
     * @return
     */
    @Query(value = "select * from department where did like ?1% and length(did)>?2 ",nativeQuery = true)
    List<Department> findTotalSubDepartment(String did, int superDidLength);
    List<Department> findByDmanager(long manager);
    List<Department> findByCreatetime(Date createtime);

}
