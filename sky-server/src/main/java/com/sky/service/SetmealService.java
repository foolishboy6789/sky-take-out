package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void addSetmeal(SetmealDTO setmealDTO);

    SetmealVO getSetmealWithDishById(Long id);

    void startOrStop(Integer status, Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void deleteSetmealByIds(List<Long> ids);

    List<Setmeal> getListByCategoryId(Long categoryId);

    List<DishItemVO> getDishItemById(Long id);
}
