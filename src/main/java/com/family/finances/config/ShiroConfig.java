package com.family.finances.config;

import com.family.finances.config.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean sf = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        sf.setSecurityManager(securityManager);
        // 没有登陆的路径
        sf.setLoginUrl("/");
        // 没有权限的路径
        sf.setUnauthorizedUrl("/");
        return sf;
    }

    @Bean
    public MyRealm realm()
    {
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
        hcm.setHashAlgorithmName("MD5");
        hcm.setHashIterations(1);
        MyRealm myRealm = realm();
        myRealm.setCredentialsMatcher(hcm);
        securityManager.setRealm(myRealm);
        //securityManager.setRealm(realm());
        return securityManager;
    }

}
