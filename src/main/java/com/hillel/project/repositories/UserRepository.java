package com.hillel.project.repositories;

import com.hillel.project.entities.User;
import com.hillel.project.exceptions.AccessDeniedException;
import com.hillel.project.exceptions.NotFoundException;
import lombok.NonNull;
import java.io.FileNotFoundException;
import java.util.List;

public interface UserRepository {
    UserRepository REPOSITORY = new UserRepositorySerialized();

    static UserRepository getInstance() {
        return REPOSITORY;
    }

    void save(User user);
    @NonNull
    User findByLogin(String login) throws NotFoundException;
    List<User> usersList();
    void deleteUserByLogin(String login);
}
