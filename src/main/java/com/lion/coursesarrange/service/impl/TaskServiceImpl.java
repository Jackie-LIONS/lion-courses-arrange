package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.TaskMapper;
import com.lion.coursesarrange.model.pojo.Task;
import com.lion.coursesarrange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Page<Task> selectAll(Integer currentPage, Integer pageSize) {
       return taskMapper.selectPage(new Page(currentPage,pageSize),null);
    }

    @Override
    public  Page<Task>  selectByCondition(Task task, Integer currentPage, Integer pageSize) {
        //分页 按条件查询
        return taskMapper.selectByCondition(task,new Page(currentPage,pageSize));
    }

    @Override
    public void deleteByIds(int[] ids) {
        taskMapper.deleteByIds(ids);
    }

    @Override
    public void update(Task newTask) {
        taskMapper.updateById(newTask);
    }

    @Override
    public void add(Task task) {
       taskMapper.insert(task);
    }

}
