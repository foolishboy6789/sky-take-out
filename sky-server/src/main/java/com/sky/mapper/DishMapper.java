package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    @Select("select count(id) from dish where category_id=#{id}")
    Integer countByCategoryId(Long id);

    List<Dish> getDishPage(DishPageQueryDTO dishPageQueryDTO);


    @Insert("insert into dish(category_id,name,price,image,description,status,create_time,update_time,create_user,update_user)" +
            " values (#{categoryId},#{name},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addDish(Dish dish);

}
