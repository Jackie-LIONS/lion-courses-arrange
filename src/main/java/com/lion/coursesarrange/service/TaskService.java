package com.lion.coursesarrange.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Task;

public interface TaskService {

    public Page<Task> selectAll(Integer currentPage, Integer pageSize);

    public  Page<Task>  selectByCondition(Task task , Integer currentPage, Integer pageSize);

    public void deleteByIds(int[] ids);

    public void update(Task task);

    public void add(Task task);


}
