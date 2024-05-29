package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface TaskMapper extends BaseMapper<Task> {


    public Task selectById(int id);

    //按各个名称模糊查询
    public Page<Task> selectByCondition(Task task,Page<Task> page);
    //按各个id精确查找
    public Task selectByCondition2(Task task);

    public void deleteByIds(int[] ids);
}
