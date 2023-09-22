package com.softwaremanager.schedulebuilder.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softwaremanager.schedulebuilder.Entity.Users;
import com.softwaremanager.schedulebuilder.Exception.UserNotFoundException;
import com.softwaremanager.schedulebuilder.repository.UsersRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UsersRepository repo;

    @Override
    public Users getUser(String username) {
        Optional<Users> userOptional = repo.findByUsername(username);
        return unwrapUser(userOptional, 404L);
    }

    private Users unwrapUser(Optional<Users> user, Long id) {
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

}
