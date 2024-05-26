package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.UserMapper;
import com.lion.coursesarrange.model.enums.CodeEnum;
import com.lion.coursesarrange.model.pojo.User;
import com.lion.coursesarrange.model.result.BusException;
import com.lion.coursesarrange.service.UserService;
import com.lion.coursesarrange.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null){
            throw new BusException(CodeEnum.USERNAME_NOT_EXIT_ERROR);
        }
        if (!password.equals(user.getPassword())){
            throw new BusException(CodeEnum.USERNAME_NOT_EXIT_ERROR);
        }
        if ("禁用".equals(user.getStatusStr())){
            throw new BusException(CodeEnum.LOGIN_USER_FORBIDDEN_ERROR);
        }
        // 生成JWT令牌，返回令牌
        String sign = JWTUtil.sign(user);
        return sign;
    }

    @Override
    public void register(User user) {
        if (user == null || user.getPassword() == null){
            throw new BusException(CodeEnum.REQISTER_NULL_ERROR);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        User userOne = userMapper.selectOne(userQueryWrapper);
        if (userOne != null){
            throw new BusException(CodeEnum.REGISTER_REPEAT_NAME_ERROR);
        }
        userMapper.insert(user);
    }

    @Override
    public Page<User> selectByCondition(User user, Integer page, Integer size) {
        Page userPage = userMapper.selectByCondition(new Page(page, size), user);
        return userPage;
    }

    @Override
    public void disableByIds(int[] ids) {
        userMapper.disableByIds(ids);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User selectById(Integer uid) {
        return userMapper.selectById(uid);
    }
}
