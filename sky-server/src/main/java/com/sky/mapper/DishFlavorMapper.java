package com.sky.mapper;


import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    @Select("select * from dish_flavor where dish_id=#{id}")
    List<DishFlavor> getFlavorByDishId(Long id);

    void deleteDishFlavorByDishIds(List<Long> ids);


    @Delete("delete from dish_flavor where dish_id=#{id}")
    void deleteDishFlavorByDishId(Long id);

    void addDishFlavors(List<DishFlavor> flavors);
}
