package com.hillel.project.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class Message implements Serializable {
    String receiverLogin;
    String senderLogin;
    String textMessage;
    
    Date date;

    public Message(String recieverLogin, String senderLogin, String textMessage){
        this.receiverLogin = recieverLogin;
        this.senderLogin = senderLogin;
        this.textMessage = textMessage;
        this.date = new Date();

    }

    public String toString(){
        return ("From: " + senderLogin + "\n" + "To: " + receiverLogin + "\n" + "Message: " + textMessage + "\n" + "Sended: " + date + "\n");
    }
}
