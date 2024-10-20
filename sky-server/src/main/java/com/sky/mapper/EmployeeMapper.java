package com.sky.mapper;

import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    @Insert("insert into employee" +
            " values (null,#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status}" +
            ",#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addEmployee(Employee employee);

    List<Employee> getEmployeePage(EmployeePageQueryDTO employeePageQueryDTO);

    void update(Employee employee);
}
