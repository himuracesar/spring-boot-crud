/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.controller;

import com.himura.videogamesdb.dto.PlatformDTO;
import com.himura.videogamesdb.services.PlatformService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to web services for platforms
 * @author Cesar Himura 
 */
@RestController
@RequestMapping("/api/platforms")
public class PlatformController {
    
    @Autowired
    private PlatformService platformService;
    
    /**
     * Web Service POST method to create platforms
     * @param platformMap Data request
     * @return 
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createPlatform(@RequestBody Map<String, Object> platformMap){
        PlatformDTO dto = new PlatformDTO();
        
        dto.setName((String) platformMap.get("name"));
        dto.setOwner((String) platformMap.get("owner"));
        
        platformService.create(dto);
        
        Map<String, String> map = new HashMap();
        map.put("message", "Platform created successfully.");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    /**
     * Web Service to get all the platforms
     * @param request
     * @return 
     */
    @GetMapping("")
     public ResponseEntity<List<PlatformDTO>> getAllPaltforms(HttpServletRequest request) {
         List<PlatformDTO> list = platformService.findAll();
         
         return new ResponseEntity<>(list, HttpStatus.OK);
     }
}
