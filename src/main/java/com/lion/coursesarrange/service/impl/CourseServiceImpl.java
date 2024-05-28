package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.CourseMapper;
import com.lion.coursesarrange.model.pojo.Course;
import com.lion.coursesarrange.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Page<Course> selectByCondition(Course course, Integer currentPage, Integer pageSize) {
        return null;
    }

    @Override
    public void update(Course course) {
        courseMapper.updateById(course);
    }

    @Override
    public void deleteByIds(int[] ids) {

    }

    @Override
    public void add(Course course) {
        courseMapper.insert(course);
    }
}
