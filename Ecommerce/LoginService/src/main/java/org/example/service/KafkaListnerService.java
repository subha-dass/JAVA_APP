package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Service
public class KafkaListnerService {
    private static final String  CART_COUNT_DETAIL="cart_count";

    @Autowired
    private SimpMessagingTemplate messagingTemplate;





    @KafkaListener(topics = {CART_COUNT_DETAIL}, groupId = "jbdl123")
    public void updateCartCount(String msg) throws ParseException, JsonProcessingException {
        System.out.println("msg is"+msg);
        messagingTemplate.convertAndSend("/topic/response", msg);

    }

}
