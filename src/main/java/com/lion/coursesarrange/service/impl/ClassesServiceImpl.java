package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.ClassesMapper;
import com.lion.coursesarrange.model.pojo.Classes;
import com.lion.coursesarrange.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public Page<Classes> selectByCondition(Classes classes, Integer page, Integer size) {
        return classesMapper.selectByCondition(classes,new Page(page,size));
    }

    @Override
    public void update(Classes classes) {
       classesMapper.updateById(classes);
    }

    @Override
    public void deleteByIds(int[] ids) {
        classesMapper.deleteByIds(ids);
    }

    @Override
    public void add(Classes classes) {
        classesMapper.insert(classes);
    }
}
