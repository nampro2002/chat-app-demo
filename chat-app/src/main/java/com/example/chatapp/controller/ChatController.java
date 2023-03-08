package com.example.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.chatapp.model.Message;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message receiverMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message") // /app/message
    @SendTo("/chatroom/public")
    public Message receiverPrivateMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getRecevicerName(), "/private", message); // /user/username/private
        return message;
    }
}
