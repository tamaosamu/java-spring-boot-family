package com.family.finances.model.request;

import lombok.Data;

@Data
public class UserRegeditRequest {

    private String username;
    private String password;

    public UserRegeditRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
