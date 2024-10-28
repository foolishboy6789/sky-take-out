package com.sky.mapper;


import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart selectByCondition(ShoppingCart shoppingCart);

    void updateNumber(ShoppingCart shoppingCart1);


    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time)"
            + " values(#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{amount},#{createTime})")
    void addShoppingCart(ShoppingCart shoppingCart);


    @Select("select * from shopping_cart where user_id = #{currentId} order by create_time desc")
    List<ShoppingCart> getShoppingCartList(Long currentId);


    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);

    
    @Delete("delete from shopping_cart where user_id = #{currentId} ")
    void clean(Long currentId);
}
