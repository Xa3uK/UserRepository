package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;

public class ShowAllUsersPermission implements Permission {
    @Override
    public String getMessage() {
        return "Show all users";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getShowAllUsersController();
    }
}

