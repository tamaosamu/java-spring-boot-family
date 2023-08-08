package com.family.finances.core;

import java.util.HashMap;

public enum ResponseCode {
    SUCCESS(0),
    FAIL(99);

    private final int code;

    ResponseCode(int code) {
        this.code = code;

    }

    public int code() {
        return code;
    }

}
