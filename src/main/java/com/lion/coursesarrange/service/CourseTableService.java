package com.lion.coursesarrange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.CourseTable;
import com.lion.coursesarrange.model.pojo.Task;

import java.util.List;

public interface CourseTableService {


    public Page<CourseTable> selectByCondition(CourseTable courseTable, Integer currentPage, Integer pageSize);

    public Page<CourseTable> selectEmptyRoom(CourseTable courseTable,Integer currentPage,Integer pageSize);

    public List<CourseTable> selectTable(CourseTable courseTable);

    public Page<Task> selectTask(Task task, Integer currentPage, Integer pageSize);


    public void deleteByIds(int[] ids);

    public String update(CourseTable courseTable);

    public String add(CourseTable courseTable);

    public boolean GA();


}
