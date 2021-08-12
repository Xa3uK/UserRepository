package com.hillel.project.entities;


import com.hillel.project.entities.permissions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private final String login;
    private String pass;
    private final List<Permission> adminPermissions = Arrays.asList(
            new ShowAllUsersPermission(),
            new ChangePassPermission(),
            new DeleteUserPermission(),
            new AddRemovePermissions(),
            new MessageSenderPermission(),
            new MessageReaderPermission(),
            new LogOutPermission(),
            new ExitPermission()
    );

    private final List<Permission> userPermissions = new ArrayList<>(Arrays.asList(
            new MessageSenderPermission(),
            new MessageReaderPermission(),
            new LogOutPermission(),
            new ExitPermission())
    );

    public String toString(){
        return String.format("%-22s%-22s", "Login:  " + login, "Password:  " + pass);
    }
}
