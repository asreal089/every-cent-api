package com.ever.cent.service.impl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ever.cent.mock.user.UserMock;
import com.ever.cent.repository.RoleRepository;
import com.ever.cent.repository.UserRepository;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByEmail() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(UserMock.getUser());
        assert(userServiceImpl.findUserByEmail("teste@teste.com") != null);
    }

    @Test
    void testFindUserById() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(UserMock.getUser()));

        assert(userServiceImpl.findUserById(1L).isPresent());
    }

    @Test
    void testProcessUserRegistration() {

    }

    @Test
    void testRegisterNewUser() {

    }
}
