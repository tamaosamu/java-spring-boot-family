package com.family.finances.model.request;

import com.family.finances.model.domain.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginResponse {

    private Serializable token;
    private User user;

}
