package com.lion.coursesarrange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Course;
import com.lion.coursesarrange.model.result.BaseResult;
import com.lion.coursesarrange.service.ClassesService;
import com.lion.coursesarrange.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    /**
     * 更新班级信息
     * @param course
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody Course course){
        courseService.update(course);
        return BaseResult.ok();
    }
    /**
     * 条件查询
     * @param course
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectByCondition")
    public BaseResult<Page<Course>> selectByCondition(@RequestBody  Course course,
                                                       @RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "5") Integer size){
        Page<Course> classesPage = courseService.selectByCondition(course, page, size);
        return BaseResult.ok(classesPage);
    }

    @PostMapping("/add")
    public BaseResult add(@RequestBody  Course course){
        courseService.add(course);
        return BaseResult.ok();
    }
    @PostMapping("/deleteIds")
    public BaseResult deleteIds(int[] ids){
        courseService.deleteByIds(ids);
        return BaseResult.ok();
    }


}
