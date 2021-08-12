package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.MessageSenderController;
import com.hillel.project.entities.User;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.repositories.MessageRepository;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.views.commandline.CliSimpleAskView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;


public class CliMessageSenderController implements MessageSenderController {

    @Override
    public boolean start() {
        CliSimpleAskView senderAskView = new CliSimpleAskView("Do you want to send message?");
        return senderAskView.ask();
    }

    public void sender(User user) {
        CliSimpleValuableAskView receiverAsk = new CliSimpleValuableAskView("Send to:");
        String receiverLogin = receiverAsk.ask();

        try {
            UserRepository.getInstance().findByLogin(receiverLogin);
        } catch (NotFoundException e) {
            System.out.println("User: " + receiverLogin + " not found");
            System.out.println();
            return;
        }
        CliSimpleValuableAskView message = new CliSimpleValuableAskView("Type your message for " + receiverLogin);
        String newMessage = message.ask();

        String senderLogin = user.getLogin();

        MessageRepository.getInstance().sendMessage(receiverLogin, senderLogin, newMessage);
    }
}
