/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Videogames Authorization Exception
 * @author Cesar Himura
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class VgAuthException extends RuntimeException{
    
    public VgAuthException(String message) {
        super(message);
    }
}
