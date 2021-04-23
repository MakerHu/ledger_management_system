package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.dao.DepartmentDao;
import com.bjtu.ledger_management_system.dao.RoleDao;
import com.bjtu.ledger_management_system.dao.UserDao;
import com.bjtu.ledger_management_system.dao.UsersRolesDao;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.entity.UsersRoles;
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
    public static List<Long> removeDuplicate(List<Long> list){
        List<Long> listTemp = new ArrayList<Long>();
        for (Long aLong : list) {
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

}
