package com.lion.coursesarrange.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.ClassesCourse;

public interface ClassesCourseService {

    public Page<ClassesCourse> selectByCondition(ClassesCourse cclasses, Integer page, Integer size);

    public void update(ClassesCourse cclasses);

    public void deleteByIds(int[] ids);

    public void add(ClassesCourse cclasses);

}
