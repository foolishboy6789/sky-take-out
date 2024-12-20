package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    PageResult getDishPage(DishPageQueryDTO dishPageQueryDTO);

    void addDishWithFlavor(DishDTO dishDTO);

    DishVO getDishWithFlavorById(Long id);

    void deleteDishByIds(List<Long> ids);

    void startOrStop(Integer status, Long id);

    void updateDishWithFlavor(DishDTO dishDTO);

    List<Dish> getDishList(Long categoryId);

    List<DishVO> getDishVOList(Long categoryId);
}
