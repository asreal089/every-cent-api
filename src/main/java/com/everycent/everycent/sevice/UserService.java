package com.everycent.everycent.sevice;

import com.everycent.everycent.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}