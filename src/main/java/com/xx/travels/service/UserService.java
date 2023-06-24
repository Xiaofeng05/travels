package com.xx.travels.service;

import com.xx.travels.entity.User;

public interface UserService {
    void register(User user);
    User login(User user);
}
