package com.lion.coursesarrange.model.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//课程班级
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String classesCourseName;
    private Integer size;
    private Course course;
    private String remark;
}
