package com.wechat.core.common.enums;

/**
 * @author ruqiang
 * @version 1.0.0
 * @className: ResponseCode
 * @Description
 * @create 2019/11/21
 */
public enum ResponseCode {

    /**
     * success
     */
    SUCCESS(0, "成功"),

    /**
     * failed
     */
    FAILED(1, "失败"),


    /**
     * Invalid length
     */
    INVALID_LENGTH(10001, "无效长度"),

    /**
     * Username cannot be empty
     */
    EMPTY_USERNAME(10101, "用户名不能为空"),

    /**
     * Password cannot be empty
     */
    EMPTY_PASSWORD(10102, "密码不能为空"),

    /**
     * Account does not exist
     */
    INVALID_USERNAME(10103, "帐号不存在"),

    /**
     * Password error
     */
    INVALID_PASSWORD(10104, "密码错误"),

    /**
     * Invalid account
     */
    INVALID_ACCOUNT(10105, "无效帐号"),
    /**
     * Account Logiend
     */
    ACCOUNT_LOGINED(10106, "账号已登录"),

    /**
     * Login Failed
     */
    LOGIN_FAILED(10107, "登录失败"),

    /**
     * Unauthorized
     */
    UNAUTHORIZED(10108, "未被授权"),

    /**
     * Invalid Roles
     */
    INVALID_ROLES(10109, "未分配角色"),

    /**
     * Unauthorized_Data
     */
    UNAUTHORIZED__DATA(10110, "没有从数据库读取到权限"),

    /**
     * State Error
     */
    STATE_ERROR(10111, "state错误"),

    /**
     * Code State Require
     */
    CODE_STATE_REQUIRE(10112, "code或state不能为空"),

    /**
     * SystemInfo error
     */
    SYSTEM_ERROR(50000, "系统错误"),
    /**
     * Illegal parameter
     */
    ILLEGAL_PARAM(50001, "非法参数");

    private int code;

    private String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
