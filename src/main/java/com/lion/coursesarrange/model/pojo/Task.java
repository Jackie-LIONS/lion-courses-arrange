package com.lion.coursesarrange.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by pxw on 2022/4/22 15:11
 *
 * @author pxw
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    private Integer id;
    private ClassesCourse cclasses;
    private Teacher teacher;
    private Integer startWeek;
    private Integer endWeek;
    private Integer weekNode;
}
