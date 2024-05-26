package com.lion.coursesarrange.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String teacherName;
    private String password;
    private String remark;
}
