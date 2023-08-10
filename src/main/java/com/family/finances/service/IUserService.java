package com.family.finances.service;

import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserRegeditRequest;

public interface IUserService {

    boolean add(UserRegeditRequest user);

    boolean remove(int id);

    User fetchOne(String username);

    User login(String username, String password);
}
