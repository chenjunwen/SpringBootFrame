package com.tuling.service;

import com.tuling.mapper.OrderMpper;
import com.tuling.modal.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/4.
 */
@Service
public class OrderService extends AbstractService<Order>{
    @Autowired
    OrderMpper orderMpper;
    public void createOrder(Order order){
        System.out.println("插入前："+order.getId());
        int insert = orderMpper.insert(order);
        System.out.println("插入后:"+insert);
    }
}
