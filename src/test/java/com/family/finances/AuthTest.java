package com.family.finances;

import com.family.finances.config.shiro.MyRealm;
import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserLoginResponse;
import com.family.finances.model.request.UserRegeditRequest;
import com.family.finances.service.IUserService;
import net.minidev.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthTest extends FinancesApplicationTests {

    private final IUserService userService;


    @Autowired
    public AuthTest(IUserService userService) {
        this.userService = userService;
    }

    @Test
    public void TestRegister()
    {
        UserRegeditRequest user = new UserRegeditRequest("steven", "a12345");
        userService.add(user);
    }

//    @Test
//    public void TestLoginUrl()
//    {
//        String url = "http://127.0.0.1:8083/auth/login?username=steven&password=a12345";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        // 处理响应结果
//        System.out.println(response.getBody());
//    }

    @Test
    public void TestLogin()
    {
        DefaultSecurityManager dfm = new DefaultSecurityManager();

        MyRealm myRealm = new MyRealm();

        HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();

        hcm.setHashAlgorithmName("MD5");
        hcm.setHashIterations(1);
        myRealm.setCredentialsMatcher(hcm);

        dfm.setRealm(myRealm);

        SecurityUtils.setSecurityManager(dfm);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("steven", "a12345");

        try {
            subject.login(token);
            System.out.println("ok");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
