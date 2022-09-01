package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

public class UserService extends CrudService<User> {
    UserService(UserRepository repository) {
        super(repository);
    }
}
