package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.ShowAllUsersController;
import com.hillel.project.entities.User;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.views.commandline.CliSimpleAskView;

import java.util.List;

public class CliShowAllUsersController implements ShowAllUsersController {
    public boolean start() {
        CliSimpleAskView showAllUsersAskView = new CliSimpleAskView("Do you want to show all users?");
        return showAllUsersAskView.ask();
    }

    public void showAllUsers() {
        List<User> users = UserRepository.getInstance().usersList();
        users.stream().sorted((user1, user2) -> user1.getLogin().compareTo(user2.getLogin())).
                forEach(System.out::println);
        System.out.println();
    }
}
