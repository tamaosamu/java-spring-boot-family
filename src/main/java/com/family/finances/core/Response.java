package com.family.finances.core;


import lombok.Data;

/**
 * 统一接口响应结果封装
 * インタフェス
 * @param <T>
 */
@Data
public class Response<T> {

    private int code;
    private String message;
    private T data;

    Response() {}

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }
    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }


}
