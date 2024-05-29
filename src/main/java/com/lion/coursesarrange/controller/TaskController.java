package com.lion.coursesarrange.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Task;
import com.lion.coursesarrange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("/selectAll")
    public Page<Task> selectAll(@RequestParam Integer currentPage, @RequestParam Integer pageSize){
        return taskService.selectAll(currentPage,pageSize);
    }

    @RequestMapping("/selectByCondition")
    public Page<Task> selectByCondition(@RequestBody Task task, @RequestParam Integer currentPage, @RequestParam Integer pageSize){
        return taskService.selectByCondition(task,currentPage,pageSize);
    }

    @RequestMapping("/deleteByIds")
    public void delete(@RequestBody int[] ids) {
        taskService.deleteByIds(ids);

    }

    @RequestMapping("/add")
    public void add(@RequestBody Task task){
         taskService.add(task);
    }

    @RequestMapping("update")
    public void update(@RequestBody Task task){
         taskService.update(task);
    }


}
