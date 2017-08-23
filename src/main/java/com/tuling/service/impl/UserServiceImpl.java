package com.tuling.service.impl;

import com.tuling.modal.User;
import com.tuling.service.AbstractService;
import com.tuling.service.UserService;
import com.tuling.utils.RedisUtil;
import com.tuling.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Service
public class UserServiceImpl  extends AbstractService<User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redis;

    @Override
    public List<User> getUsers() {
        redis.set("name","chenjunwen");
        return findAll();
    }

    @Override
    public User login(String name) {
        return userMapper.login(name);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @Override
    public Integer createUser(User user) {
        return userMapper.createUser(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delUser(Integer id) {
        deleteById(id);//使用通用插件
        return userMapper.delUser(id);
    }

    /**
     * 根据id获取user
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }


}
