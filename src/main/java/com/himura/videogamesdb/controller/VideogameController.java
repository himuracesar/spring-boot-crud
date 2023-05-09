/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.controller;

import com.himura.videogamesdb.dto.PlatformDTO;
import com.himura.videogamesdb.dto.VideogameDTO;
import com.himura.videogamesdb.services.VideogameService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web Services for videogames
 * @author Cesar Himura
 */
@RestController
@RequestMapping("/api/videogames")
public class VideogameController {
    
    @Autowired
    private VideogameService videogameService;
    
    /**
     * Web Service to create a videogame. Method: POST
     * @param videogameMap
     * @return 
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createVideogame(@RequestBody Map<String, Object> videogameMap){
        VideogameDTO videogameDto = new VideogameDTO();
        PlatformDTO platformDto = new PlatformDTO();
        
        platformDto.setId((Integer)videogameMap.get("id_platform"));
        
        videogameDto.setTitle((String) videogameMap.get("title"));
        videogameDto.setPlatformDto(platformDto);
        videogameDto.setPhysicsFormat((Boolean) videogameMap.get("physics_format"));
        videogameDto.setDigitalFormat((Boolean) videogameMap.get("digital_format"));
        
        videogameService.createVideogame(videogameDto);
        
        Map<String, String> map = new HashMap();
        map.put("message", "Videogame created successfully.");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    /**
     * Web Service to get videogames. Method: GET
     * In this case is necessary the request body to send the filter for the query
     * @param videogameMap
     * @return 
     */
    @GetMapping("")
    public ResponseEntity< List<VideogameDTO> > getVideogames(@RequestBody Map<String, Object> videogameMap) {
        VideogameDTO videogameDto = new VideogameDTO();
        
        PlatformDTO platformDto = new PlatformDTO();
        
        platformDto.setId((Integer)videogameMap.get("id_platform"));
        
        videogameDto.setTitle((String) videogameMap.get("title"));
        videogameDto.setPlatformDto(platformDto);
        videogameDto.setPhysicsFormat((Boolean) videogameMap.get("physics_format"));
        videogameDto.setDigitalFormat((Boolean) videogameMap.get("digital_format"));
        
        List<VideogameDTO> list = videogameService.findVideogames(videogameDto);
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }     
    
    /**
     * Web Service to update the information of a videogame. In this exercise I take the ID of the videogame from
     * the path (PathVariable) but the ID can be in the body also (RequestBody)
     * @param request
     * @param idVideogame
     * @param videogameMap
     * @return 
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateVideogame(HttpServletRequest request, @PathVariable("id") Integer idVideogame,
                                                                @RequestBody Map<String, Object> videogameMap){
        VideogameDTO videogameDto = new VideogameDTO();
        PlatformDTO platformDto = new PlatformDTO();
        
        platformDto.setId((Integer)videogameMap.get("id_platform"));
        
        videogameDto.setId(idVideogame);
        videogameDto.setTitle((String) videogameMap.get("title"));
        videogameDto.setPlatformDto(platformDto);
        videogameDto.setPhysicsFormat((Boolean) videogameMap.get("physics_format"));
        videogameDto.setDigitalFormat((Boolean) videogameMap.get("digital_format"));
        
        videogameService.updateVideogameById(videogameDto);
        
        Map<String, String> map = new HashMap();
        map.put("message", "Videogame updated successfully.");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    /**
     * Web Service to delete a videogame in database.
     * @param idVideogame
     * @param videogameMap
     * @return 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVideogame(@PathVariable("id") Integer idVideogame, @RequestBody Map<String, Object> videogameMap){
        videogameService.deleteVideogameById(idVideogame);
        
        Map<String, String> map = new HashMap();
        map.put("message", "Videogame deleted successfully.");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
