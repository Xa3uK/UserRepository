package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.DeleteUserController;
import com.hillel.project.exceptions.AccessDeniedException;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.views.commandline.CliSimpleAskView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;


public class CliDeleteUserController implements DeleteUserController {

    public boolean start() {
        CliSimpleAskView showDeleteUserAskView = new CliSimpleAskView("Do you want to delete some user?");
        return showDeleteUserAskView.ask();
    }
    public void deleteUser() {
        CliSimpleValuableAskView deleteLoginAsk = new CliSimpleValuableAskView("Enter user Login for delete");
        UserRepository.getInstance().deleteUserByLogin(deleteLoginAsk.ask());
    }
}
