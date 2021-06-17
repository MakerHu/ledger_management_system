package com.bjtu.ledger_management_system.service;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
import com.bjtu.ledger_management_system.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    void add(User user);
    void update(User user);
    void del(long id);
    User findById(Long id);

    User findByEmail(String email);
    List<Role> getAllDepartRoles(String did, boolean isExpand);
    Page<User> findPage(Integer pageNum, Integer pageSize, String name);
    List<User> findUsersList(List<Long>userList);
    List<Long> findUidList(List<Role>roleList);
    <User> Page<User> listConvertToPage(List<User> list, Integer pageNum, Integer pageSize);

    List<Role> getUserAllRoles(long uid);
    List<Right> getRightsInThisDepartment(long uid, String did);
    List<Right> getRightsInDepartment(long uid, String did);
    Page<User> getAllUsers(int pageNum, int pageSize);
    Page<UserMsgDTO> getAllUsersWithRoles(int pageNum, int pageSize);
    List<Role> getRolesInDepartment(String did, long uid);
    List<Department> getUserDepartments (long uid);

    boolean isNewUser(long uid);    //判断用户是否拥有角色进而确定是否为新用户

    /**
     * 模糊查找用户
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<User> getSpecificUser(String content, Integer pageNum, Integer pageSize);
}
