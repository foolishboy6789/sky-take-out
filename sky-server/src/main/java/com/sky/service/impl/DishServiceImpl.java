package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.utils.SerializeUtil;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public PageResult getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = (Page<DishVO>) dishMapper.getDishPage(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void addDishWithFlavor(DishDTO dishDTO) {
        cleanRedisCache("dish_" + dishDTO.getId());
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.addDish(dish);
        Long id = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(id);
            }
        }
        dishFlavorMapper.addDishFlavors(flavors);
    }

    @Override
    @Transactional
    public DishVO getDishWithFlavorById(Long id) {
        DishVO dishVO = new DishVO();
        Dish dish = dishMapper.getDishById(id);
        BeanUtils.copyProperties(dish, dishVO);
        List<DishFlavor> flavors = dishFlavorMapper.getFlavorByDishId(id);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

    @Override
    @Transactional
    public void deleteDishByIds(List<Long> ids) {
        for (Long id : ids) {
            cleanRedisCache("dish_" + id);
        }
        for (Long id : ids) {
            Dish dish = dishMapper.getDishById(id);
            if (Objects.equals(dish.getStatus(), StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        if (setmealDishMapper.getSetmealIdByDishIds(ids) != null && !setmealDishMapper.getSetmealIdByDishIds(ids).isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        dishMapper.deleteDishByIds(ids);
        dishFlavorMapper.deleteDishFlavorByDishIds(ids);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        cleanRedisCache("dish_*");
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);
    }

    @Override
    @Transactional
    public void updateDishWithFlavor(DishDTO dishDTO) {
        cleanRedisCache("dish_*");
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);
        dishFlavorMapper.deleteDishFlavorByDishId(dishDTO.getId());
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishDTO.getId());
            }
            dishFlavorMapper.addDishFlavors(flavors);
        }
    }

    @Override
    public List<Dish> getDishList(Long categoryId) {
        return dishMapper.getDishList(categoryId);
    }

    @Override
    public List<DishVO> getDishVOList(Long categoryId) {
        String key = "dish_" + categoryId;
        String dishList = stringRedisTemplate.opsForValue().get(key);
        if (dishList != null && !dishList.isEmpty()) {
            return (List<DishVO>) SerializeUtil.deserialize(dishList, List.class);
        } else {
            List<DishVO> list = dishMapper.getDishVOList(categoryId);
            for (DishVO dishVO : list) {
                List<DishFlavor> flavors = dishFlavorMapper.getFlavorByDishId(dishVO.getId());
                dishVO.setFlavors(flavors);
            }
            stringRedisTemplate.opsForValue().set(key, SerializeUtil.serialize(list));
            return list;
        }
    }

    private void cleanRedisCache(String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (keys != null) {
            stringRedisTemplate.delete(keys);
        }
    }

}
