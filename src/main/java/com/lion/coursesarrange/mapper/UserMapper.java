package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    @ResultMap("userResultMap")
    public List<User> findAll();

    public Page<User> selectByCondition(Page<User> page, @Param("user") User user);


    @Select("select * from user where u_name= #{userName} and password=#{password}")
    @ResultMap("userResultMap")
    public User select(String userName,String password);

    @Insert("insert  into user values (null,#{userName},#{password},#{status})")
    public void add(User user);

    @Select("select * from user where u_name = #{userName}")
    public User selectByName(String userName);

    @Update("update user set u_name =#{userName},password = #{password},status=#{status} where id = #{id}")
    public void update(User user);

    @Delete("delete from user where id = #{id}")
    public void deleteById(Integer id);

    public void disableByIds(int[] ids);

    //配置文件实现sql语句
    public User findById(int id);


}
