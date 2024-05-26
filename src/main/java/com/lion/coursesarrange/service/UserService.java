package com.lion.coursesarrange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.User;

public interface UserService {
    public String login(String username, String password);

    public Page<User> selectByCondition(User user, Integer page, Integer size);

    public void register(User user);

    public void disableByIds(int[] ids);

    public void update(User user);

    public User selectById(Integer uid);

//    public String deleteById(User user);
}
