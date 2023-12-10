package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.LoginDTO;
import org.example.DTO.MessageDTO;
import org.example.DTO.UserLoginDTO;
import org.example.DTO.UserRespDTO;
import org.example.LoginConfig.JwtUtils;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    JwtUtils jwtUtil;
    @PostMapping("/signin/create")
    public String CreateUser(@RequestBody @Valid LoginDTO loginDTO){
        User user=userService.create(loginDTO.to(),loginDTO.getPassword());
        if(user==null){
            return "Unable to create User";
        }
        return "User Creaed"+jwtUtil.generateToken(loginDTO.getUsername());
    }
    @GetMapping("/signin/jwttoken/{username}")
    public String tokenCreate(@PathVariable String username){
        return jwtUtil.generateToken(username);
    }
    @PostMapping("/login/user")
    public String login(@RequestBody @Valid UserLoginDTO userLoginDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),
                            userLoginDTO.getPassword())
            );
        }catch (Exception e){
            throw new Exception("Invalid username password");
        }

        userService.KafkaCartNoti(userLoginDTO.getUsername());
        String jwtToken="Bearer "+jwtUtil.generateToken(userLoginDTO.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url="http://localhost:7078/cart/count/"+userLoginDTO.getUsername();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseData="";
        if (response.getStatusCode().is2xxSuccessful()) {
            responseData = response.getBody();
            System.out.println("return count value is"+responseData);
        }else {}

        return "login Sucessfull "+jwtUtil.generateToken(userLoginDTO.getUsername())+" "+responseData;
    }
    @GetMapping("/login/islogin")
    public ResponseEntity<?> check( ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("username is"+username);
        if (authentication != null && authentication.isAuthenticated()) {
            // User is authenticated
            return ResponseEntity.ok(Map.of("isLoggedIn", true));
        } else {
            // User is not authenticated
            return ResponseEntity.ok(Map.of("isLoggedIn", false));
        }
    }

    @GetMapping("/login/fetch/{username}")
    public ResponseEntity<UserRespDTO> getUser(@PathVariable String username){
        User userdet=userService.getUserdetails(username);
        UserRespDTO userRespDTO=new UserRespDTO();
        System.out.println("user is "+userdet);
        userRespDTO.setId(userdet.getId());
        userRespDTO.setUsername(userdet.getUsername());
        userRespDTO.setEmail(userdet.getEmail());
        return new ResponseEntity<>(userRespDTO, HttpStatus.CREATED);
    }


}
