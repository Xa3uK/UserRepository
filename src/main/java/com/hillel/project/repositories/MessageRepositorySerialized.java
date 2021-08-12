package com.hillel.project.repositories;

import com.hillel.project.entities.Message;
import com.hillel.project.entities.User;



import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositorySerialized implements MessageRepository, AutoCloseable {
    private static final String REPOSITORY_DIR = "message-repository";
    private static final String REPOSITORY_FILE = "messages.ser";
    private static final String REPOSITORY_PATH = REPOSITORY_DIR + File.separator + REPOSITORY_FILE;
    private List<Message> messageRepository;

    MessageRepositorySerialized() {
        loadMessageRepository();
    }

    private void saveMessageRepository() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(REPOSITORY_PATH)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(messageRepository);
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
                        objectOutputStream.writeObject(messageRepository);
                    }
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void loadMessageRepository() {
        try (FileInputStream fileInputStream = new FileInputStream(REPOSITORY_PATH)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                messageRepository = (List<Message>) objectInputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            messageRepository = new ArrayList<>();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> readMessage(User user) {
        List<Message> userMessages = new ArrayList<>();
        for (Message message : messageRepository) {
            if (user.getLogin().equals(message.getReceiverLogin())) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public void sendMessage(String receiverLogin, String senderLogin, String newMessage) {
        MESSAGE_REPOSITORY.save(new Message(receiverLogin, senderLogin, newMessage));
    }

    public void save(Message message) {
        messageRepository.add(message);
    }

    public void close() {
        saveMessageRepository();
    }
}


