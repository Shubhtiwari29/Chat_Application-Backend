package com.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // yeh method kya karra hai ki ->
        // message brocker ek intermediatery cheez hoti hai jo message ko server pr route karti hai


        config.enableSimpleBroker("/topic");
        // /topic/messages

        config.setApplicationDestinationPrefixes("/app");
        // /app/chat
        // server-side: @MessagingMapping("/chat)


    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")//connection establishment
                .setAllowedOrigins("http://localhost:5173")
                .withSockJS();
    }
    //STOPM -> SIMPLE TEST ORIENTED MESSAGING PROTOCOL, IT WORKS ON THE PUBLISHER-SUBSCRIBER MODEL (JUST IKE A KAFKA-ZOOKEEPER)
    // /chat endpoint par connection apka establish hoga
}