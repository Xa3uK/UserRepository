package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;

public class ChangePassPermission implements Permission{
    @Override
    public String getMessage() {
        return "Change pass";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getChangePassController();
    }
}

