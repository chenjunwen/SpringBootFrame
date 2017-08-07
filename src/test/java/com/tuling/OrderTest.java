package com.tuling;

import com.tuling.modal.Order;
import com.tuling.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class OrderTest {

    @Autowired
    OrderService orderService;


    /**
     * 创建订单
     */
    @Test
    public void createOrder(){
        Order order = new Order();
        order.setCreatedTime(new Date());
        order.setLever(1);
        order.setPrice(100);
        order.setStatus(1);
        order.setUserAssetsId(1);
        order.setStopProfit(100);
        order.setStopLoss(10);
        orderService.createOrder(order);

        int id = order.getId();
        System.out.println(id);
    }

}
