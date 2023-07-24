package com.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.model.Userdetails;
import com.user.repo.Userrepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserdetailService {
    private static final String USER_CREATE_TOPIC = "user_created";
    @Autowired
    Userrepo userrepo;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    public String create(Userdetails userdetails) throws JsonProcessingException {
//        try {
//            userrepo.save(userdetails);
//            return "User Created";
//        }
//       catch (Exception e){
//            return "User not Created";
//       }

        Userdetails checksave = userrepo.save(userdetails);
        JSONObject userObj;
        if (checksave == null) {
            System.out.println("Saved user valuie " + checksave);
            return "User not created";
        } else {
            userObj = new JSONObject();
            userObj.put("phone", userdetails.getPhone());
            userObj.put("userId", userdetails.getId());
            userObj.put("email", userdetails.getEmail());
        }
        kafkaTemplate.send(USER_CREATE_TOPIC,
                this.objectMapper.writeValueAsString(userObj));
        return "User Created";
    }
}
