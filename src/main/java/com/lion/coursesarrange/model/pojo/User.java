package com.lion.coursesarrange.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private Integer status;

    @TableField(exist = false)
    private String statusStr;

    public String getStatusStr() {
        if (status == 0){
            return "禁用";
        }else {
            return "启用";
        }
    }
}
