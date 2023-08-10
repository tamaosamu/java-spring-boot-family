package com.family.finances.api.auth;

import com.family.finances.core.Response;
import com.family.finances.core.ResponseGenerator;
import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserRegeditRequest;
import com.family.finances.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserApi {

    private final IUserService userService;

    @Autowired
    public UserApi(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public Response<User> register(@RequestBody UserRegeditRequest userRequest)
    {
        boolean userAddRet = userService.add(userRequest);
        if(!userAddRet) {
            return ResponseGenerator.fail("注册失败");
        }
        User user = userService.fetchOne(userRequest.getUsername());
        return ResponseGenerator.success(user, "注册成功");
    }

    @DeleteMapping("/unregister")
    public Response<Boolean> unregister(
        @RequestParam(required = true) int userId
    )
    {
        boolean userRemoveRet = userService.remove(userId);
        return ResponseGenerator.success(userRemoveRet, "注销成功");
    }

    @GetMapping("/login")
    public Response<User> login(
        @RequestParam(required = true) String username,
        @RequestParam(required = true) String password
    )
    {
        User user = userService.login(username, password);
        return ResponseGenerator.success(user, "登陆成功");
    }

}
