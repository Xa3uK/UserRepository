package com.hillel.project.repositories;

import com.hillel.project.entities.User;
import com.hillel.project.exceptions.AccessDeniedException;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;
import lombok.NonNull;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepositorySerialized implements UserRepository, AutoCloseable {
    private static final String REPOSITORY_DIR = "user-repository";
    private static final String REPOSITORY_FILE = "users.ser";
    private static final String REPOSITORY_PATH = REPOSITORY_DIR + File.separator + REPOSITORY_FILE;
    private List<User> userRepository;

    UserRepositorySerialized() {
        loadRepository();
    }

    private void saveRepository() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(REPOSITORY_PATH)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(userRepository);
            }
        } catch (FileNotFoundException e) {
            try {
                File dirFile = Paths.get(REPOSITORY_DIR).toFile();
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                File repositoryFile = new File(REPOSITORY_DIR, REPOSITORY_FILE);
                if (!repositoryFile.exists()) {
                    repositoryFile.createNewFile();
                }

                try (FileOutputStream fileOutputStream = new FileOutputStream(REPOSITORY_PATH)) {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                        objectOutputStream.writeObject(userRepository);
                    }
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void loadRepository() {
        try (FileInputStream fileInputStream = new FileInputStream(REPOSITORY_PATH)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                userRepository = (List<User>) objectInputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            userRepository = new ArrayList<>();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NonNull User findByLogin(String login) throws NotFoundException {
        for (User user : userRepository) {
            if (login.equals(user.getLogin())) {
                return user;
            }
        }
        throw new NotFoundException();
    }

    public void deleteUserByLogin(String login) {
        if (!login.equals("admin")) {
            try {
                User user = findByLogin(login);
                userRepository.remove(user);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public List<User> usersList() {
        return userRepository;
    }

    @Override
    public void close() {
        saveRepository();
    }

    @Override
    public void save(User user) {
        userRepository.add(user);
    }
}




