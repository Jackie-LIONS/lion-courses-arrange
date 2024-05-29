package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.ClassesCourseMapper;
import com.lion.coursesarrange.mapper.ClassesMapper;
import com.lion.coursesarrange.model.enums.CodeEnum;
import com.lion.coursesarrange.model.pojo.ClassesCourse;
import com.lion.coursesarrange.model.result.BusException;
import com.lion.coursesarrange.service.ClassesCourseService;
import com.lion.coursesarrange.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassesCourseServiceImpl implements ClassesCourseService {
    @Autowired
    private ClassesCourseMapper classesCourseMapper;

    @Override
    public Page<ClassesCourse> selectByCondition(ClassesCourse cclasses, Integer page, Integer size) {
        return classesCourseMapper.selectByCondition(cclasses,new Page(page,size));
    }

    @Override
    public void update(ClassesCourse cclasses) {
        ClassesCourse classesCourse = classesCourseMapper.selectById(cclasses.getId());
//        已经修改
        if (!cclasses.equals(classesCourse)){
//            判断是否已存在
            List<ClassesCourse> classesCourses = classesCourseMapper.selectList(null);
            for (ClassesCourse classesCours : classesCourses) {
                if (cclasses.equals(classesCours)){
                    throw new BusException(CodeEnum.UPDATE_FAIL_ERROR);
                }
            }
        }
        classesCourseMapper.updateById(cclasses);
    }

    @Override
    public void deleteByIds(int[] ids) {
        classesCourseMapper.deleteByIds(ids);
    }

    @Override
    public void add(ClassesCourse cclasses) {
//        去掉最后的括号
        String classesCourseName = cclasses.getClassesCourseName();
        String substring = classesCourseName.substring(0, classesCourseName.length() - 1);
        cclasses.setClassesCourseName(substring);
//            判断是否已存在
        List<ClassesCourse> classesCourses = classesCourseMapper.selectList(null);
        for (ClassesCourse classesCours : classesCourses) {
            if (cclasses.equals(classesCours)){
                throw new BusException(CodeEnum.UPDATE_FAIL_ERROR);
            }
        }
        classesCourseMapper.insert(cclasses);
    }
}
