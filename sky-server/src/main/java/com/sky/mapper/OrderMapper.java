package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    void addOrder(Orders orders);

    void addOrderDetail(List<OrderDetail> orderDetails);

    @Select("select * from orders where number = #{orderNumber}")
    Orders selectByNumber(String orderNumber);

    void update(Orders orders);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id = #{id}")
    Orders selectById(Long id);


    @Select("select count(id) from orders where status=#{status}")
    Integer countStatus4Admin(Integer status);


    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> selectByStatusAndOrderTimeLt(Integer status, LocalDateTime orderTime);

    @Select("select sum(amount) from orders where order_time between #{beginTime} and #{endTime} and status = #{completed}")
    Double sumAmounts(LocalDateTime beginTime, LocalDateTime endTime, Integer completed);

    Integer sumOrders(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    List<OrderDetail> top10(LocalDateTime beginTime, LocalDateTime endTime);
}
