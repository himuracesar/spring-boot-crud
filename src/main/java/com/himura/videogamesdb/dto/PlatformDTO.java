/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.dto;

/**
 * Data Transfer Object for the platform
 * @author Cesar Himura
 */
public class PlatformDTO {
    
    private int id;
    private String name;
    private String owner;

    /**
     * Get the ID of the platform
     * @return ID of the platform
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the platform
     * @param id ID of the platform
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of the platform
     * @return the name of the platform
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the platform
     * @param name Name of the platform
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the owner
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set the owner
     * @param owner Name of the owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
