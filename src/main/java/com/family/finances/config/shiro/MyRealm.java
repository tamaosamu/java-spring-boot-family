package com.family.finances.config.shiro;

import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserLoginResponse;
import com.family.finances.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection
    ) {
        System.out.println("————权限认证————");
        return new SimpleAuthorizationInfo();
    }

    /**
     * 验证当前登录的Subject
     * UserApi.login()方法中执行Subject.login()时 执行此方法
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token
    )
    {
        System.out.println("————身份认证方法————");

        String username = token.getPrincipal().toString();

        //    Subject subject = SecurityUtils.getSubject();
        //    User user = (User) subject.getSession().getAttribute("user");
        User user = userService.fetchOne(username);
        if(user == null) {
            throw new UnknownAccountException();
        }
//        User user = new User();
//        user.setUsername("steven");
//        user.setPassword("af7fe587134d7a2683d30dbac1936261");
//        user.setSalt("^VFn$i");

        ByteSource salt = ByteSource.Util.bytes(username + user.getSalt());
        log.info("salt -> {}", salt);
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(
            username,
            user.getPassword(),
            salt,
            getName()
        );

        // 将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        return authInfo;
    }

}
