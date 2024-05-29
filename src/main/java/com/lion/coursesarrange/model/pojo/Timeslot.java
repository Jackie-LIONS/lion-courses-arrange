package com.lion.coursesarrange.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot implements Serializable {
    private Integer timeId;
    private String timeslot;
}
