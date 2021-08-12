package com.hillel.project.entities.permissions;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.Controller;

public class MessageSenderPermission implements Permission {
    @Override
    public String getMessage() {
        return "Send message";
    }

    @Override
    public Controller getController() {
        return ApplicationConfig.FACTORY.getMessageSenderController();
    }
}
