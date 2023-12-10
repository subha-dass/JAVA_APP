package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaListner {
    private static final String CART_FETCH_DETAILS="fetch_cart";

    private static final String CART_COUNT_DETAIL="cart_count";

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ItemService itemService;

    private ObjectMapper objectMapper=new ObjectMapper();

    @KafkaListener(topics = {CART_FETCH_DETAILS}, groupId = "jbdl123")
    public void updateTransaction(String msg) throws ParseException, JsonProcessingException {

        JSONObject obj = (JSONObject) new JSONParser().parse(msg);
        String username = (String) obj.get("username");

//        Integer CartCount=itemService.getCartCount(username);
//        System.out.println("Cart Count is "+CartCount);
//
//        JSONObject cartcount=new JSONObject();
//        cartcount.put("CartCount",CartCount);
//        cartcount.put("username",username);


//        kafkaTemplate.send(CART_COUNT_DETAIL, objectMapper.writeValueAsString(cartcount));
    }
}
