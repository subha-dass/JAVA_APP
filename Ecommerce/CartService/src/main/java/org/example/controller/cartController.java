package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.example.dto.ItemDetaildto;
import org.example.dto.ProductResponseDTO;
import org.example.model.ItrmDetail;
import org.example.model.ProductCart;
import org.example.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class cartController {
    ItrmDetail itemdetails;
    @Autowired
    ItemService itemService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/cart/count/{userName}")
    public Integer CartCount(@PathVariable String userName){
        System.out.println("id is 5555");
        Integer id=itemService.getCartUsername(userName);
        System.out.println("id is gggg"+id);
        return itemService.getCartCount(id);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<ItrmDetail> additem(@RequestBody @Valid ItemDetaildto itemDetaildto, @RequestHeader("Authorization") String jwtToken){
        System.out.println("jwt token"+jwtToken);
        String token = jwtToken.substring(7);
        String username = extractUsernameFromToken(token);
        String url ="http://localhost:7085/login/fetch/"+username;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String name="";
        int age=0;

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseData = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Parse the JSON string into a JsonNode
                JsonNode jsonNode = objectMapper.readTree(responseData);

                // Now you can access the data using the JsonNode
                name = jsonNode.get("username").asText();
                age = jsonNode.get("id").asInt();
                String email = jsonNode.get("email").asText();

                // Process the data as needed
            } catch (Exception e) {
                // Handle the exception if the parsing fails
                e.printStackTrace();
            }
            // Process the data from the other microservice
            System.out.println("response is "+responseData);
        } else {
            // Handle the error
            System.out.println("response not success");
        }
        ItrmDetail itrmDetail1=itemService.getUserData(name);
        Integer prod_id=null;
        if(itrmDetail1 != null) {
            System.out.println("before getting prod details");
            prod_id = itemService.getProddetail(itrmDetail1.getId());
        }
        System.out.println("item details usernamer is"+" "+prod_id);
        if(itrmDetail1 != null && itrmDetail1.getUsername().equals(name) && prod_id != null){
            itemdetails=itemService.add(itemDetaildto.to1(name,age,itrmDetail1));
            System.out.println("itrmDetail1...if cond...check");
        }else {
            System.out.println("else cond cart");
            itemdetails = itemService.add1(itemDetaildto.to(name, age));
            System.out.println("itrmDetail1...else cond...check");
        }
        return new ResponseEntity<>(itemdetails,HttpStatus.CREATED);
    }

    @DeleteMapping("/cart/delete/{slno}")
    public String deleteProduct(@PathVariable String slno){
        itemService.deletImem(slno);
        return "deleted...";
    }

    @GetMapping("/cart/fetch")
    public ResponseEntity<List<ProductResponseDTO>> grtCartdata(@RequestHeader("Authorization") String jwtToken){
        System.out.println("jwt token"+jwtToken);
        String token = jwtToken.substring(7);
        String username = extractUsernameFromToken(token);
        List<ItrmDetail> itrmDetail=itemService.getAllCartdata(username);
        System.out.println("itrmdetails and"+itrmDetail.toString());
        List<ProductResponseDTO> productResponseDTOS=itrmDetail.stream().flatMap(itrmDetail1 -> itrmDetail1.getProductlist().stream()
                .map(product ->{ProductResponseDTO productResponseDTO =  modelMapper.map(product, ProductResponseDTO.class);
                    byte[] imageBytes = new byte[0]; // Fetch image bytes
                    try {
                        imageBytes = getImageBytes(product.getImageFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    productResponseDTO.setImage(imageBytes);
                    return productResponseDTO;})).collect(Collectors.toList());
        return new ResponseEntity<>(productResponseDTOS,HttpStatus.OK);
    }
    private String extractUsernameFromToken(String jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
        return claims.getSubject();
    }
    private byte[] getImageBytes(String imageFilename) throws IOException{
        Path imagePath=Paths.get("C:\\JavaScript_FE\\FSJS\\react_new\\ecommercen\\src\\image\\" + imageFilename);
        return Files.readAllBytes(imagePath);
    }
}
