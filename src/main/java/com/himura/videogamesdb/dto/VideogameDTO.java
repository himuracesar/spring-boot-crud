/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.dto;

import java.sql.Date;

/**
 * Data Transfer Object for Videogames
 * @author Cesar Himura 
 */
public class VideogameDTO {
    private int id;
    private String title;
    private boolean physicsFormat;
    private boolean digitalFormat;
    private Date createdAt;
    
    private PlatformDTO platformDto;

    /**
     * Get the ID of the videogame
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the videogame
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the title of the videogame
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the videogame
     * @param title Title of the videogame
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get if the videogame is physics format
     * @return 
     */
    public boolean isPhysicsFormat() {
        return physicsFormat;
    }

    /**
     * Set if the videogame is physics format
     * @param physicsFormat 
     */
    public void setPhysicsFormat(boolean physicsFormat) {
        this.physicsFormat = physicsFormat;
    }

    /**
     * Get if the videogame is digital format
     * @return 
     */
    public boolean isDigitalFormat() {
        return digitalFormat;
    }

    /**
     * Set if the videogame is digital format
     * @param digitalFormat 
     */
    public void setDigitalFormat(boolean digitalFormat) {
        this.digitalFormat = digitalFormat;
    }

    /**
     * Get the date of creation the record in database
     * @return 
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the date of creation
     * @param createdAt Date of creation
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the information of the platform
     * @return 
     */
    public PlatformDTO getPlatformDto() {
        return platformDto;
    }

    /**
     * Set the platform
     * @param platformDto 
     */
    public void setPlatformDto(PlatformDTO platformDto) {
        this.platformDto = platformDto;
    }
}
