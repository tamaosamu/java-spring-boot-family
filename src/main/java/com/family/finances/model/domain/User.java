package com.family.finances.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
    private Integer id;

    @NotEmpty(message = "用户名不可为空")
    private String username;

    @JsonIgnore
    @NotEmpty(message = "密码不可为空")
    @Length(min = 6, message = "密码位数不能少于6")
    private String password;

    @JsonIgnore
    private String salt;

    @Tolerate
    public User() {}
}

