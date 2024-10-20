package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    PageResult getCategoryPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void startOrStop(Integer status, Long id);

    void addCategory(CategoryDTO categoryDTO);

    void updateCategory(CategoryDTO categoryDTO);

    List<Category> getCategoryList(String type);

    void deleteCategoryById(Long id);
}
