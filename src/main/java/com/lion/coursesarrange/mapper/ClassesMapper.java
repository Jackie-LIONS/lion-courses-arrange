package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Classes;
import com.lion.coursesarrange.model.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {


    public Page<Classes> selectByCondition(@Param("classes") Classes classes, Page<User> page);


    public void deleteByIds(int[] ids);
}
