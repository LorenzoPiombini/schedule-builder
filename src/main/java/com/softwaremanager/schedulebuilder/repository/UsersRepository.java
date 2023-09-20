package com.softwaremanager.schedulebuilder.repository;

import org.springframework.data.repository.CrudRepository;

import com.softwaremanager.schedulebuilder.Entity.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {

}
