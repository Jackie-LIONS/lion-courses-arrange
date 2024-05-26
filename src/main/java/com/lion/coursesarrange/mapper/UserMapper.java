package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    public Page<User> selectByCondition(Page<User> page, @Param("user") User user);


    @Insert("insert  into user values (null,#{userName},#{password},#{status})")
    public void add(User user);


    public void disableByIds(int[] ids);


}
