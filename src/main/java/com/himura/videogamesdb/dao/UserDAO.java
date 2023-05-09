/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.himura.videogamesdb.dao;

import com.himura.videogamesdb.dto.UserDTO;
import com.himura.videogamesdb.exceptions.VgAuthException;

/**
 * Data Access Object for users
 * @author Cesar Himura
 */
public interface UserDAO {
    
    /**
     * Create the users
     * @param dto Information of the user
     * @throws VgAuthException 
     */
    public void create(UserDTO dto) throws VgAuthException;
    
    /**
     * Get coount users by username
     * @param username Username
     * @return 
     */
    public Integer getCountByUsername(String username);
    
    /**
     * Find a user by username and password. The password is cyphered.
     * @param dto Data of the user
     * @return
     * @throws vgAuthException 
     */
    public UserDTO findByUsernameAndPassword(UserDTO dto) throws VgAuthException;
}
