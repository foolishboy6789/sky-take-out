package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    @Select("select count(id) from setmeal where category_id=#{id}")
    public Integer countByCategoryId(Long id);

    List<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Insert("insert into setmeal(category_id,name,price,status,description,image,create_time,update_time,create_user,update_user)" +
            " values (#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addSetmeal(Setmeal setmeal);


    @Select("select * from setmeal where id=#{id}")
    Setmeal getSetmealById(Long id);


    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);


    void deleteSetmealByIds(List<Long> ids);

    @Select("select * from setmeal where category_id=#{categoryId}")
    List<Setmeal> getListByCategoryId(Long categoryId);
}
