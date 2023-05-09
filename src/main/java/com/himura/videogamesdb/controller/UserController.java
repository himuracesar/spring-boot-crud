/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.controller;

import com.himura.videogamesdb.Constants;
import com.himura.videogamesdb.dto.UserDTO;
import com.himura.videogamesdb.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web Services for Users
 * @author Cesat Himura
 */
@RestController
@RequestMapping("api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Web Service to login
     * @param userMap Request body 
     * @return 
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        UserDTO dto = new UserDTO();
        
        dto.setUsername((String) userMap.get("username"));
        dto.setPassword((String) userMap.get("password"));
        
        userService.validate(dto);
        
        /*Map<String, String> map = new HashMap();
        map.put("message", "LoggedIn successfully.");*/
        
        return new ResponseEntity<>(generateJWTToken(dto), HttpStatus.OK);
    }
    
    /**
     * Web Service for create users. Method POST
     * @param userMap Request body with the information with the users
     * @return 
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, Object> userMap){
        UserDTO dto = new UserDTO();
        
        dto.setUsername((String) userMap.get("username"));
        dto.setPassword((String) userMap.get("password"));
        
        userService.create(dto);
        
        Map<String, String> map = new HashMap();
        map.put("message", "User created successfully.");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    /**
     * Generate a token with JWT library. The token contains the necessary information of the user and
     * time valid. Each time the function is called the time expiration is updated.
     * We can check the information that contain the token and we can do it in the JWT page.
     * https://jwt.io/
     * @param dto User's data
     * @return 
     */
    private Map<String, String> generateJWTToken(UserDTO dto) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("username", dto.getUsername())
                .compact();
        
        Map<String, String> map = new HashMap();
        map.put("token", token);
        
        return map;
    }
}
