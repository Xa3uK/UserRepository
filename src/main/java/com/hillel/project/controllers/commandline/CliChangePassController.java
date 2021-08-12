package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.ChangePassController;
import com.hillel.project.entities.User;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.repositories.UserRepositorySerialized;
import com.hillel.project.views.commandline.CliSimpleAskView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;

public class CliChangePassController implements ChangePassController {
    public boolean start() {
        CliSimpleAskView showChangePassAskView = new CliSimpleAskView("Do you want to change pass?");
        return showChangePassAskView.ask();
    }
    public void changePass(User user) {
        if (user.getLogin().equals("admin")) {

            CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter user Login");
            String login = loginAsk.ask();
            CliSimpleValuableAskView newPassAsk = new CliSimpleValuableAskView("Enter new password");
            String newPassword = newPassAsk.ask();

            try {
                User user1 = UserRepository.getInstance().findByLogin(login);
                user1.setPass(newPassword);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            CliSimpleValuableAskView newPassAsk = new CliSimpleValuableAskView("Enter your new password");
            String newPassword = newPassAsk.ask();
            user.setPass(newPassword);
        }
    }
}