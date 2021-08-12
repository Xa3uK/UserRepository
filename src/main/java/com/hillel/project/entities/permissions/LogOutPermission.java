package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;


public class LogOutPermission implements Permission{
    @Override
    public String getMessage() {
        return "Log out";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getLogoutController();
    }
}
