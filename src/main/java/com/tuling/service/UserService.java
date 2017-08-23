package com.tuling.service;

import com.github.pagehelper.Page;
import com.tuling.modal.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface UserService {
    /**
     *  获取用户信息
     * @return
     */
    Page<User> getUsers(Map<String,String> params);

    /**
     * 登录
     * @param name
     * @return
     */
    User login(String name);

    /**
     * 修改
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 新增
     * @param user
     * @return
     */
    Integer createUser(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delUser(Integer id);

    /**
     * 根据id获取user
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
