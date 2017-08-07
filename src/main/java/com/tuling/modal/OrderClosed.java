package com.tuling.modal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/4.
 */
@ApiModel("成交单")
public class OrderClosed implements Serializable {
    @ApiModelProperty("成交单id")
    private int id;

    @ApiModelProperty(value = "被撮合订单",notes = "从哪开始")
    private int fromOrder;

    @ApiModelProperty("撮合订单")
    private int toOrder;

    @ApiModelProperty("撮合数量")
    private int quantity;

    @ApiModelProperty("撮合类型")
    private int orderType;

    @ApiModelProperty("撮合时间")
    private Date createTime;

    @ApiModelProperty(value = "成交订单状态",notes = "1为可用")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromOrder() {
        return fromOrder;
    }

    public void setFromOrder(int fromOrder) {
        this.fromOrder = fromOrder;
    }

    public int getToOrder() {
        return toOrder;
    }

    public void setToOrder(int toOrder) {
        this.toOrder = toOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

enum  OrderType{
    BUY(1),
    SELL(-1);

    private int status;

    OrderType(int status){
        this.status = status;
    }
}
