package com.bjtu.ledger_management_system.dao;

import com.bjtu.ledger_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DepartmentDao extends JpaRepository<Department,String>  {
    Department findByDname(String name);
    List<Department> findByDmanager(long manager);
    List<Department> findByCreatetime(Date createtime);

}
