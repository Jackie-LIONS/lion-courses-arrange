package com.lion.coursesarrange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Room;

public interface RoomService {

    public Page<Room> selectByCondition(Room room, Integer page, Integer size);

    public void update(Room room);

    public void deleteByIds(int[] ids);

    public void add(Room room);

}
