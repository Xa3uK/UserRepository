package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.*;
import com.hillel.project.entities.User;
import com.hillel.project.entities.permissions.ChangePassPermission;
import com.hillel.project.entities.permissions.DeleteUserPermission;
import com.hillel.project.entities.permissions.Permission;
import com.hillel.project.entities.permissions.ShowAllUsersPermission;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.repositories.UserRepositorySerialized;
import com.hillel.project.views.commandline.CliControllerRunnableMenuView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;


import java.util.Iterator;
import java.util.List;


public class CliPermissionsController implements PermissionController {


    public void addRemovePermissions() {
        Controller controller = new CliControllerRunnableMenuView()
                .withOptions("Delete User permission", new CliDeleteUserController())
                .withOptions("Change pass permission", new CliChangePassController())
                .withOptions("ShowAllUsers permission", new CliShowAllUsersController())
                .runAndGetNextController();


        if (controller instanceof DeleteUserController) {
            CliSimpleValuableAskView answerAsk = new CliSimpleValuableAskView("Add or remove? \n 1. Add \n 2. Remove");
            switch (answerAsk.ask()) {
                case "1":
                    addDeleteUserPermission();
                    break;
                case "2":
                    removeDeleteUserPermission();
                    break;
            }
        }

        if (controller instanceof ChangePassController) {
            CliSimpleValuableAskView answerAsk = new CliSimpleValuableAskView("Add or remove? \n 1. Add \n 2. Remove");
            switch (answerAsk.ask()) {
                case "1":
                    addChangePassPermission();
                    break;
                case "2":
                    removeChangePassPermission();
                    break;
            }
        }

        if (controller instanceof ShowAllUsersController) {
            CliSimpleValuableAskView answerAsk = new CliSimpleValuableAskView("Add or remove? \n 1. Add \n 2. Remove");
            switch (answerAsk.ask()) {
                case "1":
                    addShowAllUsersPermission();
                    break;
                case "2":
                    removeShowAllUsersPermission();
                    break;
            }
        }
    }

    private void addDeleteUserPermission() {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter User login");
        adder(loginAsk.ask(), new DeleteUserPermission());
    }

    private void removeDeleteUserPermission() {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter User login");
        remover(loginAsk.ask(), new DeleteUserPermission());
    }

    private void addChangePassPermission() {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter User login");
        adder(loginAsk.ask(), new ChangePassPermission());
    }

    private void removeChangePassPermission() {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter User login");
        remover(loginAsk.ask(), new ChangePassPermission());
    }

    private void addShowAllUsersPermission() {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter User login");
        adder(loginAsk.ask(), new ShowAllUsersPermission());
    }

    private void removeShowAllUsersPermission() {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter User login");
        remover(loginAsk.ask(), new ShowAllUsersPermission());
    }


    public void adder(String login, Permission permission) {
        try {
            User user = UserRepositorySerialized.REPOSITORY.findByLogin(login);
            user.getUserPermissions().add(0, permission);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void remover(String login, Permission permission) {
        try {
            User user = UserRepositorySerialized.REPOSITORY.findByLogin(login);
            List<Permission> permissions = user.getUserPermissions();
            Iterator<Permission> it = permissions.iterator();
            while (it.hasNext()) {
                if (it.next().getMessage().equals(permission.getMessage())) {
                    it.remove();
                    break;
                }
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
