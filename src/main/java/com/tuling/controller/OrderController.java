package com.tuling.controller;

import com.tuling.modal.Order;
import com.tuling.modal.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
@RequestMapping("/api/order")
@Api(tags = "委托单",description="委托单操作api")
public class OrderController {
    @ApiOperation(value = "创建订单",notes = "根据所传的信息创建订单",authorizations = {@io.swagger.annotations.Authorization("token")})
    @ApiImplicitParam(value = "order",dataTypeClass = Order.class)
    @RequestMapping(method = RequestMethod.POST)
    public void createOrder(@RequestBody  @ApiParam(value = "创建一个订单", required = true) Order order){

    }

    @ApiOperation(value = "修改订单",notes = "根据所传的信息修改订单",authorizations = {@io.swagger.annotations.Authorization("token")},produces = "application/json")
    @ApiImplicitParam(value = "order",dataTypeClass = Order.class)
    @RequestMapping(method = RequestMethod.PATCH)
    public void updateOrder(User user){
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除订单",notes = "根据订单id修改订单")
    @ApiImplicitParam(value = "订单id",dataType = "int",required = true)
    public void deleteOrder(@PathVariable int id){
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "获取订单详情",notes = "根据订单id获取订单详情",authorizations = {@io.swagger.annotations.Authorization("token")})
    @ApiImplicitParam(value = "订单id",dataType = "int")
    public Order getOrderById(@PathVariable int id){
        return null;
    }



}
