package com.hillel.project.entities.permissions;

import com.hillel.project.controllers.Controller;

import java.io.Serializable;


public interface Permission extends Serializable {

   String getMessage();
   Controller getController();
}
