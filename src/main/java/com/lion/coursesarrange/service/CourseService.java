package com.lion.coursesarrange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Course;

public interface CourseService {
    public Page<Course> selectByCondition(Course course, Integer currentPage, Integer pageSize);

    public void update(Course course);

    public void deleteByIds(int[] ids);

    public void add(Course course);
}
