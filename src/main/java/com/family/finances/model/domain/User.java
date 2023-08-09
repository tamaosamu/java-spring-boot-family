package com.family.finances.model.domain;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class User {
    private Integer id;

    @NotEmpty(message = "用户名不可为空")
    private String username;

    @NotEmpty(message = "密码不可为空")
    @Size(min = 6, message = "密码位数不能少于6")
    private String password;

    @Tolerate
    User(){}
}

