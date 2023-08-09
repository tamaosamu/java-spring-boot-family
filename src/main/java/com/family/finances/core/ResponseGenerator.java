package com.family.finances.core;

public class ResponseGenerator {
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    public static <T> Response<T> success() {
        return new Response<T>()
                .setCode(ResponseCode.SUCCESS.code())
                .setMessage(SUCCESS_MESSAGE);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>()
                .setCode(ResponseCode.SUCCESS.code())
                .setMessage(SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> Response<T> success(T data, String message) {
        return new Response<T>()
                .setCode(ResponseCode.SUCCESS.code())
                .setMessage(message)
                .setData(data);
    }


    public static <T> Response<T> fail(String message) {
        return new Response<T>()
                .setCode(ResponseCode.FAIL.code())
                .setMessage(message);
    }

}
