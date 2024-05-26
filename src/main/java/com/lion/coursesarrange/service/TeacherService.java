package com.lion.coursesarrange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Teacher;
import com.lion.coursesarrange.model.pojo.User;

public interface TeacherService {
    public String login(String username, String password);

    public Page<Teacher> selectByCondition(Teacher teacher, Integer page, Integer size);

    public void update(Teacher teacher);

    public void deleteByIds(int[] ids);

    public void register(Teacher teacher);
    public Teacher selectById(Integer tid);
}
