package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;

public class AddRemovePermissions implements Permission{
    @Override
    public String getMessage() {
        return "Add/Remove permissions to user";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getPermissionController();
    }
}

