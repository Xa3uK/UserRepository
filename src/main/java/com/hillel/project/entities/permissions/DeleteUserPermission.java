package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;

public class DeleteUserPermission implements Permission{
    @Override
    public String getMessage() {
        return "Delete User";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getDeleteUserController();
    }
}

