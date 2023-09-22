package com.softwaremanager.schedulebuilder.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with Id: " + id + " doesn't exist");
    }
}
