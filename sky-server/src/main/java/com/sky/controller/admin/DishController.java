package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public Result<PageResult> getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品信息:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.getDishPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result<String> addDishWithFlavor(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.addDishWithFlavor(dishDTO);
        return Result.success("新增菜品成功");
    }

}
