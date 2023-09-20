package com.softwaremanager.schedulebuilder.web;

import org.springframework.web.bind.annotation.RestController;

import com.softwaremanager.schedulebuilder.service.UserServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UsersController {

    private UserServiceImpl usersService;

}
