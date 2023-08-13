package com.family.finances.service;

import com.family.finances.model.domain.User;
import com.family.finances.model.request.UserLoginResponse;
import com.family.finances.model.request.UserRegeditRequest;

public interface IUserService {

    boolean add(UserRegeditRequest user);

    boolean remove(int id);

    User fetchOne(String username);


}
