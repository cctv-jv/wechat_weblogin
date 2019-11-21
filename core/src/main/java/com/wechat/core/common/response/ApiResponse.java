package com.wechat.core.common.response;

import com.wechat.core.common.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ruqiang
 * @version 1.0.0
 * @className: ApiResponse
 * @Description wrapper result
 * @create 2019/11/21
 */
@Data
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -7855846604341970980L;
    private int code;

    private String msg;

    private T data;


    private ApiResponse(int code, String  msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static  <T> ApiResponse<T> OK(T data){
        return new ApiResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),data);
    }

    public static <T> ApiResponse<T> ERROR(int code, String msg, T data){
        return new ApiResponse<>(code,msg,data);
    }

    public static <T> ApiResponse<T> response(int code, String msg, T data){
        return new ApiResponse<>(code,msg,data);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}