package com.tuling.service.impl;

import com.github.pagehelper.Page;
import com.tuling.common.utils.Common;
import com.tuling.common.utils.MD5Util;
import com.tuling.common.utils.RedisUtil;
import com.tuling.modal.User;
import com.tuling.service.AbstractService;
import com.tuling.service.UserService;
import com.tuling.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public Page<User> getUsers(Map<String,String> params) {
        //redis.set("name","chenjunwen"); reids测试
        Condition serviceCondition = Common.getServiceCondition(params, User.class);
        List<User> orders = findByCondition(serviceCondition);
        return (Page<User>) orders;
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
        user.setCreateTime(new Date());
        user.setPassWord(MD5Util.getMD5(user.getPassWord()));
        user.setState(0);
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
