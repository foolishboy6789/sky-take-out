package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus4Admin(Integer status);
}
