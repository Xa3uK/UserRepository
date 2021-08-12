package com.hillel.project.config;

import com.hillel.project.controllers.ControllersFactory;


public class ApplicationConfig {
    public final  static ControllersFactory.Type APPLICATION_TYPE = ControllersFactory.Type.COMMAND_LINE_INTERFACE;
    public final  static ControllersFactory FACTORY = new ControllersFactory(APPLICATION_TYPE);
}
