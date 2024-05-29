package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Room;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    public Page<Room> selectByCondition(@Param("room") Room room,Page<Room> page);

    public void deleteByIds(int[] ids);



}
