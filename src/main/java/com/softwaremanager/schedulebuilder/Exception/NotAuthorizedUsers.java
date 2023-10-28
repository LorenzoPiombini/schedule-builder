package com.softwaremanager.schedulebuilder.Exception;

public class NotAuthorizedUsers extends RuntimeException {
    public NotAuthorizedUsers() {
        super("You are not authorized!");
    }

}
