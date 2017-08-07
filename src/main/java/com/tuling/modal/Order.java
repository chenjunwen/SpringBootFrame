package com.tuling.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/4.
 */
@ApiModel("委托单")
@Table(name = "tex_order")
public class Order implements Serializable{
    @Id
    @ApiModelProperty(value = "订单id 更新的情况下要传，新建的情况不用传")
    private int id;

    @Column(name = "user_assets_id")
    @ApiModelProperty("用户资金账户id")
    private int userAssetsId;

    @ApiModelProperty("订单状态")
    private int status;

    @ApiModelProperty("委托价格")
    private double price;

    @ApiModelProperty("杠杆倍数")
    private int lever;

    @ApiModelProperty("止损价格")
    private double stopLoss;

    @ApiModelProperty("止盈价格")
    private double stopProfit;

    @ApiModelProperty("订单时间")
    private Date createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserAssetsId() {
        return userAssetsId;
    }

    public void setUserAssetsId(int userAssetsId) {
        this.userAssetsId = userAssetsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getStopProfit() {
        return stopProfit;
    }

    public void setStopProfit(double stopProfit) {
        this.stopProfit = stopProfit;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userAssetsId=" + userAssetsId +
                ", status=" + status +
                ", price=" + price +
                ", lever=" + lever +
                ", stopLoss=" + stopLoss +
                ", stopProfit=" + stopProfit +
                ", createdTime=" + createdTime +
                '}';
    }
}
