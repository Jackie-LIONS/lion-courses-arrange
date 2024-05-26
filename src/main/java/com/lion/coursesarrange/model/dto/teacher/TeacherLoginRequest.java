package com.lion.coursesarrange.model.dto.teacher;

import lombok.Data;

import java.io.Serializable;
@Data
public class TeacherLoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String teacherName;
    private String password;
    private String checkCode;
}
