package com.family.finances.api.auth;

import com.family.finances.core.Response;
import com.family.finances.core.ResponseGenerator;
import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserLoginResponse;
import com.family.finances.model.request.UserRegeditRequest;
import com.family.finances.service.IUserService;
import com.family.finances.utils.Md5Utils;
import com.family.finances.utils.SaltUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class UserApi {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final IUserService userService;

    @Autowired
    public UserApi(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public Response<User> register(@RequestBody UserRegeditRequest userRequest)
    {
        if(userRequest.getUsername().isEmpty()) {
            return ResponseGenerator.fail("请输入用户名");
        }
        if (userRequest.getPassword().isEmpty()) {
            return ResponseGenerator.fail("请输入密码");
        }

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
        @RequestParam() String username,
        @RequestParam() String password
    )
    {
        if(username.isEmpty()) {
            return ResponseGenerator.fail("请输入用户名");
        }
        if(password.isEmpty()) {
            return ResponseGenerator.fail("请输入密码");
        }

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return ResponseGenerator.success((User) subject.getSession().getAttribute("user"), "登陆成功");
        }catch (UnknownAccountException e) {
            return ResponseGenerator.fail("用户不存在");
        } catch (IncorrectCredentialsException e) {
            return ResponseGenerator.fail("密码错误");
        } catch (LockedAccountException e) {
            return ResponseGenerator.fail("用户已锁定");
        } catch (AuthenticationException e) {
            log.info(" -> {}, {}", e.getClass(), e.getMessage());
            return ResponseGenerator.fail("登陆失败");
        }
    }

    @GetMapping("/user")
    public Response<User> user()
    {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return ResponseGenerator.success(user);
    }

}
