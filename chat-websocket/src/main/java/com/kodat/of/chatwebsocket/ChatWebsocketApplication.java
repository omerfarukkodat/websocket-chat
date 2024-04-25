package com.kodat.of.chatwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
public class ChatWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatWebsocketApplication.class, args);
    }

}
