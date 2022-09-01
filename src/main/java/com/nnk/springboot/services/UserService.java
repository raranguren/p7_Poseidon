package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User> {
    UserService(UserRepository repository) {
        super(repository);
    }
}
