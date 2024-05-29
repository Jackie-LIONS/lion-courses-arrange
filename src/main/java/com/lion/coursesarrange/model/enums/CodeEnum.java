package com.lion.coursesarrange.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 返回状态码枚举类
 */
@Getter
@AllArgsConstructor
public enum CodeEnum implements Serializable {
    // 正常
    SUCCESS(200, "OK"),
    // 系统异常
    SYSTEM_ERROR(500, "系统异常"),
    // 业务异常
    PARAMETER_ERROR(601, "参数异常"),
    USERNAME_NOT_EXIT_ERROR(602,"用户名错误"),
    LOGIN_NAME_PASSWORD_ERROR(603,"密码错误"),
    LOGIN_USER_FORBIDDEN_ERROR(604, "您的用户被冻结，请联系管理员"),
    CHECK_CODE_ERROR(605,"验证码不正确"),
//    REGISTER_REPEAT_PHONE_ERROR(606,"输入手机号已存在"),
    REGISTER_REPEAT_NAME_ERROR(606,"输入用户名已存在"),
    REQISTER_NULL_ERROR(607,"输入信息为空"),
    UPDATE_FAIL_ERROR(608,"更新失败"),

//    UPLOAD_FILE_ERROR(604,"文件上传失败"),


    LOGIN_CODE_ERROR(609,"登录验证码错误"),
    VERIFY_TOKEN_ERROR(611,"验证令牌失败"),
    CHECK_SIGN_ERROR(613,"验签错误"),
    ;


    private final Integer code;
    private final String message;
}
