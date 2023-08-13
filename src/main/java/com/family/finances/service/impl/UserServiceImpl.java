package com.family.finances.service.impl;

import com.family.finances.mapper.IUserMapper;
import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserRegeditRequest;
import com.family.finances.service.IUserService;
import com.family.finances.utils.Md5Utils;
import com.family.finances.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements IUserService {

    private final IUserMapper userMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserServiceImpl(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean add(UserRegeditRequest userRequest)
    {
        String username = userRequest.getUsername();
        // 用户名重复
        int countByUsername = userMapper.getCountByUsername(username);
        if(countByUsername != 0) {
            return false;
        }

        // 加密盐
        String salt = SaltUtils.get(6);
        String password = new Md5Hash(userRequest.getPassword(), username+salt).toHex();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt(salt);

        return userMapper.add(user) > 0;
    }

    @Override
    public User fetchOne(String username)
    {
        return userMapper.getByUsername(username);
    }

    public boolean remove(int id)
    {
        return userMapper.remove(id) > 0;
    }

}
