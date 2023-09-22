package com.softwaremanager.schedulebuilder.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.softwaremanager.schedulebuilder.Entity.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
