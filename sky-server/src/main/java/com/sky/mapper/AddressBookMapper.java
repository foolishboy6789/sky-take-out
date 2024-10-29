package com.sky.mapper;


import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    @Select("select * from address_book where user_id = #{currentId}")
    List<AddressBook> getAddressList(Long currentId);


    @Insert("insert into address_book (user_id, consignee, phone, sex, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default)"
            + " values (#{userId},#{consignee},#{phone},#{sex},#{provinceCode},#{provinceName},#{cityCode},#{cityName},#{districtCode},#{districtName},#{detail},#{label},#{isDefault})")
    void addAddress(AddressBook addressBook);

    AddressBook selectByCondition(AddressBook addressBook);

    void update(AddressBook addressBook);


    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);
}
