package com.family.finances.api.auth;

import com.family.finances.core.Response;
import com.family.finances.core.ResponseGenerator;
import com.family.finances.model.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserApi {

    @PostMapping("/register")
    public Response<User> register(@RequestBody User user)
    {
        return ResponseGenerator.success(user, "注册成功");
    }

}
