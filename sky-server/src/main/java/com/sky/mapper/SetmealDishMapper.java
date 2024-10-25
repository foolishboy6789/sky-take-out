package com.sky.mapper;


import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {


    List<Long> getSetmealIdByDishIds(List<Long> ids);


    void addsetmealDishes(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> getDishesBySetmealId(Long id);


    @Delete("delete from setmeal_dish where setmeal_id=#{id}")
    void deleteDishBySetmealId(Long id);

    void deleteDishBySetmealIds(List<Long> ids);


    List<DishItemVO> getDishItemById(Long id);
}
