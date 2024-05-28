package com.lion.coursesarrange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Classes;
import com.lion.coursesarrange.model.pojo.Teacher;
import com.lion.coursesarrange.model.result.BaseResult;
import com.lion.coursesarrange.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    /**
     * 更新班级信息
     * @param classes
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody Classes classes){
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
    public BaseResult<Page<Classes>> selectByCondition(@RequestBody  Classes classes,
                                                       @RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "5") Integer size){
        Page<Classes> classesPage = classesService.selectByCondition(classes, page, size);
        return BaseResult.ok(classesPage);
    }

    @PostMapping("/add")
    public BaseResult add(@RequestBody  Classes classes){
        classesService.add(classes);
        return BaseResult.ok();
    }
    @PostMapping("/deleteIds")
    public BaseResult deleteIds(int[] ids){
        classesService.deleteByIds(ids);
        return BaseResult.ok();
    }


}
