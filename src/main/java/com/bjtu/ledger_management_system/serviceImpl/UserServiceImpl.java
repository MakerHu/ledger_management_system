package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.*;
import com.bjtu.ledger_management_system.entity.*;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UsersRolesDao usersRolesDao;
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RolesRightsDao rolesRightsDao;
    @Resource
    private RightDao rightDao;
    /**
     * 添加用户
     * @param user
     * 用户对象
     */
    @Override
    public void add(User user) {
        userDao.save(user);
    }

    /**
     * 更新用户
     * @param user
     * 用户对象
     */
    @Override
    public void update(User user) {
        userDao.save(user);
    }

    /**
     * 删除用户
     * @param id
     * 用户u_id
     */
    @Override
    public void del(long id) {
        userDao.deleteById(id);
    }

    /**
     * 根据id查询用户
     * @param id
     * 用户u_id
     * @return
     * 返回符合条件的用户对象
     */
    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    /**
     * 根据邮箱查询用户
     * @param email
     * 用户邮箱
     * @return
     * 返回符合条件的用户对象
     */
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 翻页数据
     * @param pageNum
     * 前端当前页数，加一即需要返回的页
     * @param pageSize
     * 每页的数据条数
     * @param name
     * 搜索的值
     * @return
     * 返回分页对象
     */
    @Override
    public Page<User> findPage(Integer pageNum, Integer pageSize, String name) {
        return null;
    }

    /**
     * 根据传过来的的角色列表在userole表中找到uid集合
     * @param roleList
     * @return
     */
    @Override
    public List<Long> findUidList(List<Role> roleList){
        List<Long> uidList = new ArrayList<>();
        for (Role role : roleList) {
            for (UsersRoles usersroles : usersRolesDao.findByRoleid(role.getRoleid())) {
                uidList.add(usersroles.getUid());
            }
        }
        return removeDuplicate(uidList);
    }

    /**
     * 对list进行去重
     * @param list
     * @return
     */
    public static <T> List<T> removeDuplicate(List<T> list){
        List<T> listTemp = new ArrayList<>();
        for (T aLong : list) {
            if (!listTemp.contains(aLong)) {
                listTemp.add(aLong);
            }
        }
        return listTemp;
    }

    /**
     * 从用户表中取出User
     * @param userList
     * @return
     */
    @Override
   public List<User> findUsersList(List<Long> userList){
        List<User> list=new ArrayList<>();
       for(int i=0;i<userList.size();i++){
           list.add(userDao.findByUid(userList.get(i)));
       }

       return list;
    }

    @Override
    public List<Role> getAllDepartRoles(String did, boolean isExpand) {
            if (!isExpand) {
                List<Role> roleList = roleDao.findByDid(did);
                return roleList;
            } else {
                List<Role> roleList = roleDao.findByDidStartingWith(did);
                return roleList;
            }

    }

    /**
     * userList转换为Page
     * @param list
     * @param pageNum
     * @param pageSize
     * @param <User>
     * @return
     */
    @Override
    public <User> Page<User> listConvertToPage(List<User> list,Integer pageNum, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNum-1,pageSize);
        // 当前页第一条数据在List中的位置
        int start = (int)pageable.getOffset();
        // 当前页最后一条数据在List中的位置
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        // 配置分页数据
        if(start > end)return null;
        return new PageImpl<User>(list.subList(start, end), pageable, list.size());
    }

    /**
     * 获取某用户的所有角色
     * @param uid 用户编号
     * @return
     */
    @Override
    public List<Role> getUserAllRoles(long uid) {
        List<UsersRoles> usersRolesList = usersRolesDao.findByUid(uid);
        List<Role> roleList = new ArrayList<>();
        for (UsersRoles usersRoles : usersRolesList) {
            Role role = roleDao.findById(usersRoles.getRoleid()).orElse(null);
            roleList.add(role);
        }
        return roleList;
    }

    /**
     * 获取用户在此部门下的权限，不包括从上级继承来的权限
     * @param uid
     * @param did
     * @return
     */
    @Override
    public List<Right> getRightsInThisDepartment(long uid, String did) {
        List<Role> rolesList = getRolesInDepartment(did,uid);
        List<Right> requestRightList = new ArrayList<>();
        // 遍历所有的角色
        for (Role role : rolesList){
            List<RolesRights> rolesRightsList = rolesRightsDao.findByRoleid(role.getRoleid());
            // 遍历该角色的所有权限id
            for (RolesRights rolesRights : rolesRightsList){
                Right right = rightDao.findByRightid(rolesRights.getRightid());
                // 去重
                if(right != null && !requestRightList.contains(right)){
                    requestRightList.add(right);
                }
            }
        }
        return requestRightList;
    }

    /**
     * 获取某用户在某部门下的所有权限
     * @param uid 用户编号
     * @param did 部门编号
     * @return
     */
    @Override
    public List<Right> getRightsInDepartment(long uid, String did) {
        List<Right> requestRightList = new ArrayList<>();
        for(int i=0;i<did.length();){
            String thisDid = did.substring(0,i+1);
            List<Right> rightsInThisDepartment = getRightsInThisDepartment(uid,thisDid);
            requestRightList.addAll(rightsInThisDepartment);
            i+=2;
        }
        return removeDuplicate(requestRightList);
    }

    /**
     * 获取系统中的所有用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getAllUsers(int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum-1,pageSize);
        Page<User> allusersPage = userDao.findAll(pageable);
        for (User user : allusersPage.getContent()) {
            user.setPassword("");
        }
        return allusersPage;
    }

    /**
     * 获取某用户在某部门的角色
     * @param did
     * @param uid
     * @return
     */
    @Override
    public List<Role> getRolesInDepartment(String did, long uid) {
        //从users_roles表中获取用户的所有角色编号
        List<UsersRoles> usersRolesList = usersRolesDao.findByUid(uid);
        List<Role> roleList = new ArrayList<>();
        for (UsersRoles usersRoles : usersRolesList) {
            Role role = roleDao.findByRoleidAndDid(usersRoles.getRoleid(),did);
            if (role!=null){
                roleList.add(role);
            }
        }
        return roleList;
    }

    /**
     * 获取某用户所在的所有部门
     * @param uid
     * @return
     */
    @Override
    public List<Department> getUserDepartments(long uid) {
        List<Role> rolesList = getUserAllRoles(uid);
        List<Department> requestDepartmentList = new ArrayList<>();
        for (Role role : rolesList) {
            Department department = departmentDao.findById(role.getDid()).orElse(null);
            requestDepartmentList.add(department);
        }
        return removeDuplicate(requestDepartmentList);
    }

    /**
     * 判断一个用户是否为新用户
     * @param uid
     * @return
     */
    @Override
    public boolean isNewUser(long uid) {
        if (usersRolesDao.findByUid(uid).size() == 0){
            return true;
        }else{
            return false;
        }
    }


}
