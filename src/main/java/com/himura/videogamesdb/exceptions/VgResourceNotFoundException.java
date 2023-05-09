/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for resource not found
 * @author Cesar Himura
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VgResourceNotFoundException extends RuntimeException {
    
    public VgResourceNotFoundException(String message) {
        super(message);
    }
} 
