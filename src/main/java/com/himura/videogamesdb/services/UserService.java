/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.himura.videogamesdb.services;

import com.himura.videogamesdb.dto.UserDTO;
import com.himura.videogamesdb.exceptions.VgAuthException;

/**
 * Services for users objects
 * @author Cesar Himura
 */
public interface UserService {
    
    /**
     * Create a user
     * @param dto Data of the user
     * @throws VgAuthException 
     */
    public void create(UserDTO dto) throws VgAuthException;
    
    /**
     * Validate the user
     * @param dto Data of the user
     * @throws VgAuthException 
     */
    public UserDTO validate(UserDTO dto) throws VgAuthException;
}
