package com.sky.mapper;


import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    @Insert("insert into dish_flavor(dish_id,name,value) values (#{dishId},#{name},#{value})")
    void addDishFlavor(DishFlavor dishFlavor);


    @Select("select * from dish_flavor where dish_id=#{id}")
    List<DishFlavor> getFlavorByDishId(Long id);

    void update(DishFlavor dishFlavor);


    void deleteDishFlavorByDishIds(List<Long> ids);
}
