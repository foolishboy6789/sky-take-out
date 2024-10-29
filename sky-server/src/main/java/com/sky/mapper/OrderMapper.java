package com.sky.mapper;


import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void addOrder(Orders orders);

    void addOrderDetail(List<OrderDetail> orderDetails);
}
