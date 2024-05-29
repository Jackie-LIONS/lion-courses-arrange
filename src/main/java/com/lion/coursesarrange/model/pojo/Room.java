package com.lion.coursesarrange.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String roomName;
    private Integer capacity;
    private String remark;
}
