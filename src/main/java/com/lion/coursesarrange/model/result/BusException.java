package com.lion.coursesarrange.model.result;

import com.lion.coursesarrange.model.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自定义业务异常
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusException extends RuntimeException implements Serializable {
    // 状态码（成功：200，失败：其他）
    private Integer code;
    // 异常信息
    private String message;


    public BusException(CodeEnum codeEnum){
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public BusException(CodeEnum codeEnum, String message) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage() + message;
    }
}
