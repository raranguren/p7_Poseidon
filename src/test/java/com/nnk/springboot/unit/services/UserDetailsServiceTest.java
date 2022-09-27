package com.nnk.springboot.unit.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @InjectMocks
    UserService userDetailsServiceTest;

    @Mock
    UserRepository repository;

    @Test
    public void user_not_found () {
        userDetailsServiceTest.loadUserByUsername("testName");
        verify(repository).findOneByUsername("testName");
    }

    @Test
    public void user_found () {
        User user = new User("full name", "testName", "Pass.w00rd", "USER");
        when(repository.findOneByUsername(any())).thenReturn(Optional.of(user));
        UserDetails result = userDetailsServiceTest.loadUserByUsername("testName");
        verify(repository).findOneByUsername("testName");
        assert(user.equals(result));
    }

}
