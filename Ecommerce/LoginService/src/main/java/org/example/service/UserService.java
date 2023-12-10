package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DAO.UserDAO;
import org.example.model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService  {

    @Autowired
    UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_CREATE_TOPIC = "fetch_cart";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByuserName(username);
    }

    public User create(User user,String password){
        System.out.println("password is"+password);
        user.setPassword(passwordEncoder.encode(password));
        return  userDAO.save(user);
    }

    public String KafkaCartNoti(String username) throws JsonProcessingException {
        JSONObject logincartinfo = new JSONObject();
        logincartinfo.put("username", username);
        kafkaTemplate.send(USER_CREATE_TOPIC,
                this.objectMapper.writeValueAsString(logincartinfo));
        return "Noti Send";
    }

    public User getUserdetails(String username){
        return userDAO.findByuserName(username);

    }
}
