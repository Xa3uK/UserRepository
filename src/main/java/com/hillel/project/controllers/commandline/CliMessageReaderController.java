package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.MessageReaderController;

import com.hillel.project.entities.Message;
import com.hillel.project.entities.User;
import com.hillel.project.repositories.MessageRepository;
import com.hillel.project.views.commandline.CliSimpleAskView;

import java.util.ArrayList;
import java.util.List;

public class CliMessageReaderController implements MessageReaderController {

    @Override
    public boolean start() {
        CliSimpleAskView readerAskView = new CliSimpleAskView("Do you want to read message?");
        return readerAskView.ask();
    }

    public void messageReader(User user) {
        List<Message> userMessages = new ArrayList<>(MessageRepository.getInstance().readMessage(user));
        if (userMessages.isEmpty()) {
            System.out.println("No new messages for you \n");
        } else {
            for (Message message : userMessages) {
                System.out.println(message);
            }
        }
    }
}



