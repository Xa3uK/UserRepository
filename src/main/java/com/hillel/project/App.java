package com.hillel.project;

import com.hillel.project.config.ApplicationConfig;
import com.hillel.project.controllers.ControllersFactory;
import com.hillel.project.controllers.MainController;


public class App {


    public static void main(String[] args) {

        ControllersFactory controllersFactory = ApplicationConfig.FACTORY;

        MainController mainController = controllersFactory.getMainController();
        mainController.start();
    }
}
