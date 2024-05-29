package com.lion.coursesarrange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.ClassesCourse;
import com.lion.coursesarrange.model.result.BaseResult;
import com.lion.coursesarrange.service.ClassesCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classesCourse")
public class ClassesCourseController {
    @Autowired
    private ClassesCourseService classesService;
    /**
     * 更新信息
     * @param classes
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody ClassesCourse classes){
        classesService.update(classes);
        return BaseResult.ok();
    }
    /**
     * 条件查询
     * @param classes
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectByCondition")
    public BaseResult<Page<ClassesCourse>> selectByCondition(@RequestBody  ClassesCourse classes,
                                                       @RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "5") Integer size){
        Page<ClassesCourse> classesPage = classesService.selectByCondition(classes, page, size);
        return BaseResult.ok(classesPage);
    }

    @PostMapping("/add")
    public BaseResult add(@RequestBody  ClassesCourse classes){
        classesService.add(classes);
        return BaseResult.ok();
    }
    @PostMapping("/deleteIds")
    public BaseResult deleteIds(int[] ids){
        classesService.deleteByIds(ids);
        return BaseResult.ok();
    }
}
