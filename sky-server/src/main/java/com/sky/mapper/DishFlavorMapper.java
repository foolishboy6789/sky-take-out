package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DishFlavorMapper {

    @Insert("insert into dish_flavor(dish_id,name,value) values (#{dishId},#{name},#{value})")
    void addDishFlavor(DishFlavor dishFlavor);
}
