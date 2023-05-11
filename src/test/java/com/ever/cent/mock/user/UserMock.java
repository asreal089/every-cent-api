package com.ever.cent.mock.user;

import com.ever.cent.domain.model.User;

public class UserMock {
    public static User getUser() {
        return User.builder()
                .id(1L)
                .displayName("test")
                .email("teste@teste.com.br")
                .build();
    }
}
