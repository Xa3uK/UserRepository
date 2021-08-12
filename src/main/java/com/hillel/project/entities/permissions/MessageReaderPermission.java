package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;

public class MessageReaderPermission implements Permission{
    @Override
    public String getMessage() {
        return "Read message";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getMessageReaderController();
    }
}
