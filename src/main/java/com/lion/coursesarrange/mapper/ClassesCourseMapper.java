package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.ClassesCourse;
import com.lion.coursesarrange.model.pojo.Course;
import org.apache.ibatis.annotations.*;



@Mapper
public interface ClassesCourseMapper extends BaseMapper<ClassesCourse> {

    public Page<ClassesCourse> selectByCondition(ClassesCourse classesCourse,Page<ClassesCourse> page);

    public void deleteByIds(int[] ids);



}
