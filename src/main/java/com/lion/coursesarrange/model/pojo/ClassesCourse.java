package com.lion.coursesarrange.model.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//课程班级
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesCourse {
    private Integer id;
    private String classesCourseName;
    private Integer size;
    private Course course;
    private String remark;
}
