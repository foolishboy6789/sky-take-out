package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategoryPage(CategoryPageQueryDTO categoryPageQueryDTO);

    List<Category> getCategoryList(String type);

    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    @Insert("insert into category" +
            " values (null, #{type}, #{name}, #{sort}, #{status}," +
            " #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(OperationType.INSERT)
    void addCategory(Category category);


    @Delete("delete from category where id=#{id}")
    void deleteCategoryById(Long id);
}
