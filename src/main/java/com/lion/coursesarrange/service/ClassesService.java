package com.lion.coursesarrange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Classes;

public interface ClassesService {
    public Page<Classes> selectByCondition(Classes classes, Integer page, Integer size);

    public void update(Classes classes);

    public void deleteByIds(int[] ids);

    public void add(Classes classes);
}
