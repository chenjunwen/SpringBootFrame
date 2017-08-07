package com.tuling.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/4.
 */
@ApiModel("资金账号")
public class UserAssets implements Serializable{
    @ApiModelProperty("资金账户id")
    private int id;

    @ApiModelProperty("用户id")
    private int uid;

    @ApiModelProperty(value = "用户状态",notes = "1为可用")
    private int status;

    @ApiModelProperty("可用资金")
    private double availableassets;

    @ApiModelProperty("冻结资金")
    private double frozenassest;

    @ApiModelProperty("创建时间")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getAvailableassets() {
        return availableassets;
    }

    public void setAvailableassets(double availableassets) {
        this.availableassets = availableassets;
    }

    public double getFrozenassest() {
        return frozenassest;
    }

    public void setFrozenassest(double frozenassest) {
        this.frozenassest = frozenassest;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
