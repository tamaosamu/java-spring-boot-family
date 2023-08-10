package com.family.finances.service.impl;

import com.family.finances.mapper.IUserMapper;
import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserRegeditRequest;
import com.family.finances.service.IUserService;
import com.family.finances.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements IUserService {

    private final IUserMapper userMapper;

    @Autowired
    public UserServiceImpl(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean add(UserRegeditRequest userRequest)
    {
        // 用户名重复
        int countByUsername = userMapper.getCountByUsername(userRequest.getUsername());
        if(countByUsername != 0) {
            return false;
        }

        User user = new User();
        // 加密盐
        String salt = SaltUtils.get(6);
        Md5Hash md5Hash = new Md5Hash(userRequest.getPassword(), salt, 1024);

        user.setSalt(salt);
        user.setUsername(userRequest.getUsername());
        user.setPassword(md5Hash.toHex());

        return this.userMapper.add(user) > 0;
    }

    @Override
    public User login(String username, String password)
    {
        User user = userMapper.getByUsername(username);
        if(user == null) {
            return null;
        }

        Md5Hash md5Hash = new Md5Hash(username, user.getSalt(), 1024);
        if(user.getPassword().equals(md5Hash.toHex())) {
            return null;
        }

        return user;
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
