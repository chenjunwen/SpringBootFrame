package com.tuling.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/1.
 */
@ApiModel(value = "用户实体类")
public class User implements Serializable {
    @ApiModelProperty(value = "用户id",required = false)
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "状态",example = "0表示未登录，1表示已登录")
    private String state;

    @ApiModelProperty(value = "最后登录时间",example = "2017-01-01 11:40:59")
    private Date lastLoginTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("登录次数")
    private int loginCount;
    public User(){

    }
    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", state='" + state + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                ", loginCount=" + loginCount +
                '}';
    }
}
