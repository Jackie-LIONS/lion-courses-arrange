package com.lion.coursesarrange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.pojo.CourseTable;
import com.lion.coursesarrange.model.pojo.Task;
import com.lion.coursesarrange.model.pojo.Timeslot;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseTableMapper extends BaseMapper<CourseTable> {



    public Page<CourseTable> selectByCondition(CourseTable courseTable,Page<CourseTable> page);

    public List<CourseTable> selectByCondition2(@Param("ccName") String classesName,@Param("timeId") Integer timeId,@Param("teacherId") Integer teacherId);

    public List<CourseTable> selectAll();

    public Page<CourseTable> selectEmptyRoom(CourseTable courseTable,Page<CourseTable> courseTablePage);

    public List<CourseTable> selectTable(CourseTable courseTable);

    public Page<Task> selectTask(Task task,Page<Task> page);

    @Select("select * from timeslot")
    @ResultMap("TimeslotResultMap")
    public List<Timeslot> selectAllTimeslot();

    @Update("update course_table set room_id =#{room.id},timeslot_id = #{timeslot.id} where task_id = #{task.id}")
    public void update(CourseTable courseTable);

    public void deleteByIds(int[] ids);

    public void deleteAll();

    public void addCourseTables(CourseTable[] tables);


    @Insert("insert into course_table values( #{task.id}, #{room.id}, #{timeslot.id} )")
    public void addCourseTable(CourseTable table);


}
