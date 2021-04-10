package com.bjtu.ledger_management_system.serviceImpl;

import com.bjtu.ledger_management_system.dao.UserDao;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

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
}
