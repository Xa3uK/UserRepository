package com.hillel.project.repositories;

import com.hillel.project.entities.Message;
import com.hillel.project.entities.User;

import java.util.List;


public interface MessageRepository {
    MessageRepository MESSAGE_REPOSITORY = new MessageRepositorySerialized();

    static MessageRepository getInstance() {
        return MESSAGE_REPOSITORY;
    }
    void save(Message message);
    List<Message> readMessage(User user);
    void sendMessage(String receiverLogin, String senderLogin, String newMessage);
}
