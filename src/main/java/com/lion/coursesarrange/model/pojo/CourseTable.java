package com.lion.coursesarrange.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private Task task;
    private Room room;
    private Timeslot timeslot;
}
