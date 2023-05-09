/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.dto;

/**
 * Data Transfer Object for users
 * @author Cesar Himura
 */
public class UserDTO {
    int id;
    String username;
    String password;

    /**
     * Get ID of the user
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the user
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the username
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password for the user
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
