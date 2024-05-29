package com.lion.coursesarrange.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.mapper.RoomMapper;
import com.lion.coursesarrange.model.pojo.Room;
import com.lion.coursesarrange.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Page<Room> selectByCondition(Room room, Integer page, Integer size) {
        return roomMapper.selectByCondition(room,new Page(page,size));
    }

    @Override
    public void update(Room room) {
        roomMapper.updateById(room);
    }

    @Override
    public void deleteByIds(int[] ids) {
        roomMapper.deleteByIds(ids);
    }

    @Override
    public void add(Room room) {
        roomMapper.insert(room);
    }
}
