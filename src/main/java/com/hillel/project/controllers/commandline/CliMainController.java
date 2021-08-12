package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.*;
import com.hillel.project.entities.permissions.Permission;
import com.hillel.project.entities.User;
import com.hillel.project.repositories.MessageRepository;
import com.hillel.project.repositories.MessageRepositorySerialized;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.repositories.UserRepositorySerialized;
import com.hillel.project.views.commandline.CliControllerRunnableMenuView;

import java.util.List;

public class CliMainController implements MainController {

    @Override
    public void start() {
        while (true) {
            CliLoginController cliLoginController = new CliLoginController();
            User user = cliLoginController.start();
            while (true) {
                CliControllerRunnableMenuView runnableMenuView = new CliControllerRunnableMenuView();
                List<Permission> permissions;
                switch (user.getLogin()) {
                    case "admin":
                        permissions = user.getAdminPermissions();
                        break;
                    default:
                        permissions = user.getUserPermissions();
                }

                permissions.forEach(permission -> {
                    runnableMenuView.withOptions(permission.getMessage(), permission.getController());
                });

                Controller controller = runnableMenuView.runAndGetNextController();

                if (controller instanceof MessageSenderController) {
                    ((CliMessageSenderController) controller).sender(user);
                    continue;
                }

                if (controller instanceof MessageReaderController) {
                    ((CliMessageReaderController) controller).messageReader(user);
                    continue;
                }

                if (controller instanceof ShowAllUsersController) {
                    ((CliShowAllUsersController) controller).showAllUsers();
                    continue;
                }

                if (controller instanceof PermissionController) {
                    ((CliPermissionsController) controller).addRemovePermissions();
                    continue;
                }

                if (controller instanceof DeleteUserController) {
                    ((CliDeleteUserController) controller).deleteUser();
                    continue;
                }

                if (controller instanceof ChangePassController) {
                    ((CliChangePassController) controller).changePass(user);
                    continue;
                }

                if (controller instanceof LogoutController) {
                    System.out.println("Byy!");
                    ((UserRepositorySerialized) UserRepository.getInstance()).close();
                    ((MessageRepositorySerialized) MessageRepository.getInstance()).close();
                    break;
                }

                if (controller instanceof ExitController) {
                    System.out.println("Byy!");
                    ((UserRepositorySerialized) UserRepository.getInstance()).close();
                    ((MessageRepositorySerialized) MessageRepository.getInstance()).close();
                    return;

                }
            }
        }
    }
}



