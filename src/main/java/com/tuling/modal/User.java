package com.tuling.modal;

import com.tuling.common.validator.group.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/1.
 */
@ApiModel(value = "用户实体类")
@Table(name = "users")
public class User implements Serializable {
    @Id
    @ApiModelProperty(value = "用户id",required = false)
    private Integer id;

    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class})
    @ApiModelProperty(value = "用户名")
    private String name;

    @NotBlank(message = "密码不能为空",groups = {AddGroup.class})
    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "状态",example = "0表示未登录，1表示已登录")
    private Integer state;

    @ApiModelProperty(value = "最后登录时间",example = "2017-01-01 11:40:59")
    private Date lastLoginTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty(value = "登录次数",required = false)
    private Integer loginCount;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}
