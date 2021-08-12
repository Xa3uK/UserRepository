package com.hillel.project.controllers;

import com.hillel.project.controllers.commandline.*;
import lombok.Getter;

@Getter
public class ControllersFactory {

    public enum Type {
        COMMAND_LINE_INTERFACE
    }

    private final MainController mainController;
    private final LoginController loginController;
    private final LogoutController logoutController;
    private final ExitController exitController;
    private final MessageReaderController messageReaderController;
    private final MessageSenderController messageSenderController;
    private final ShowAllUsersController showAllUsersController;
    private final DeleteUserController deleteUserController;
    private final ChangePassController changePassController;
    private final PermissionController permissionController;

    public ControllersFactory(Type type) {
        switch (type) {
            case COMMAND_LINE_INTERFACE:
                mainController = new CliMainController();
                loginController = new CliLoginController();
                logoutController = new CliLogoutController();
                exitController = new CliExitController();
                messageReaderController = new CliMessageReaderController();
                messageSenderController = new CliMessageSenderController();
                showAllUsersController = new CliShowAllUsersController();
                deleteUserController = new CliDeleteUserController();
                changePassController = new CliChangePassController();
                permissionController = new CliPermissionsController();
                break;

            default:
                throw new IllegalArgumentException("Unsupported TYPE=" + type.name());
        }
    }
}
