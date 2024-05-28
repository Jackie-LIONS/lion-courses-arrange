package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    public Page<Course> selectByCondition(@Param("course") Course course,Page<Course> page);
    public void deleteByIds(int[] ids);
}
