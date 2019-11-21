package com.github.g5h.socialify.controller;

import com.github.g5h.socialify.message.ChatMessage;
import com.github.g5h.socialify.message.RoomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessageConverter;

    @MessageMapping("/chat")
    public void greeting(ChatMessage message) throws Exception {
        log.info("Message received: {}", message);
        Thread.sleep(1000); // simulated delay
        simpMessageConverter.convertAndSend(String.format("/room/%s", message.getSongId()), new RoomMessage(message.getContent()));
    }

}