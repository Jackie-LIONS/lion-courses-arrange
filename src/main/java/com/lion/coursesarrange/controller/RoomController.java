package com.lion.coursesarrange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Room;
import com.lion.coursesarrange.model.result.BaseResult;
import com.lion.coursesarrange.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    /**
     * 更新信息
     * @param room
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody Room room){
        roomService.update(room);
        return BaseResult.ok();
    }
    /**
     * 条件查询
     * @param room
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectByCondition")
    public BaseResult<Page<Room>> selectByCondition(@RequestBody  Room room,
                                                      @RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "5") Integer size){
        Page<Room> classesPage = roomService.selectByCondition(room, page, size);
        return BaseResult.ok(classesPage);
    }

    @PostMapping("/add")
    public BaseResult add(@RequestBody Room room){
        roomService.add(room);
        return BaseResult.ok();
    }

    @PostMapping("/deleteIds")
    public BaseResult deleteIds(int[] ids){
        roomService.deleteByIds(ids);
        return BaseResult.ok();
    }
}
