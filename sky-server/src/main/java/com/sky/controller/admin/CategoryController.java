package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("adminCategoryController")
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult> getCategoryPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("查询菜品分类页面,{}", categoryPageQueryDTO);
        return Result.success(categoryService.getCategoryPage(categoryPageQueryDTO));
    }

    @GetMapping("/list")
    public Result<List<Category>> getCategoryList(String type) {
        log.info("根据类型查询菜品分类,{}", type);
        return Result.success(categoryService.getCategoryList(type));
    }

    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用或禁用分类,{},{}", status, id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    @PostMapping
    public Result<String> addCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("添加分类,{}", categoryDTO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @PutMapping
    public Result<String> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类,{}", categoryDTO);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteCategoryById(Long id) {
        log.info("删除分类,{}", id);
        categoryService.deleteCategoryById(id);
        return Result.success();
    }
}
