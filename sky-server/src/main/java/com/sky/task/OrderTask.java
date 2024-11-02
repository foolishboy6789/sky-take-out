package com.sky.task;


import com.sky.Websocket.WebsocketServer;
import com.sky.constant.MessageConstant;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private WebsocketServer websocketServer;

    @Scheduled(cron = "0 * * * * ?")
    public void checkOrderStatus() {
        log.info("定时检查订单状态");

        List<Orders> ordersList = orderMapper.selectByStatusAndOrderTimeLt(
                Orders.PENDING_PAYMENT,
                LocalDateTime.now().minusMinutes(15)
        );
        if (ordersList != null && !ordersList.isEmpty()) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason(MessageConstant.ORDER_TIME_OUT);
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public void deliveryOrder() {
        log.info("处理定时派送订单");
        List<Orders> ordersList = orderMapper.selectByStatusAndOrderTimeLt(
                Orders.DELIVERY_IN_PROGRESS,
                LocalDateTime.now().minusMinutes(60)
        );
        if (ordersList != null && !ordersList.isEmpty()) {
            for (Orders orders : ordersList) {
                orders.setDeliveryStatus(Orders.COMPLETED);

                orderMapper.update(orders);
            }
        }
    }

    // @Scheduled(cron = "0/5 * * * * ?")
    public void WebsocketTest() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        websocketServer.sendToAllClient(time);
    }
}
