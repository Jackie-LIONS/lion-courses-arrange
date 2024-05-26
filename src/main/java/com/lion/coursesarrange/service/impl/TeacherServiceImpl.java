package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.TeacherMapper;
import com.lion.coursesarrange.model.enums.CodeEnum;
import com.lion.coursesarrange.model.pojo.Teacher;
import com.lion.coursesarrange.model.result.BusException;
import com.lion.coursesarrange.service.TeacherService;
import com.lion.coursesarrange.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public String login(String teacherName, String password) {
        QueryWrapper<Teacher> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("teacherName",teacherName);
        Teacher teacher = teacherMapper.selectOne(userQueryWrapper);
        if (teacher == null){
            throw new BusException(CodeEnum.USERNAME_NOT_EXIT_ERROR);
        }
        if (!password.equals(teacher.getPassword())){
            throw new BusException(CodeEnum.USERNAME_NOT_EXIT_ERROR);
        }
        // 生成JWT令牌，返回令牌
        String sign = JWTUtil.signTeacher(teacher);
        return sign;
    }

    @Override
    public Page<Teacher> selectByCondition(Teacher teacher, Integer page, Integer size) {
        return teacherMapper.selectByCondition(teacher,new Page(page,size));
    }

    @Override
    public void update(Teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    @Override
    public void deleteByIds(int[] ids) {
        teacherMapper.deleteByIds(ids);
    }

    @Override
    public void register(Teacher teacher) {
        LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
        teacherQueryWrapper.eq(Teacher::getTeacherName,teacher.getTeacherName());
        Teacher teacherOne = teacherMapper.selectOne(teacherQueryWrapper);
        if (teacherOne != null){
            throw new BusException(CodeEnum.REGISTER_REPEAT_NAME_ERROR);
        }
        teacherMapper.insert(teacher);
    }

    @Override
    public Teacher selectById(Integer tid) {
        return teacherMapper.selectById(tid);
    }
}
