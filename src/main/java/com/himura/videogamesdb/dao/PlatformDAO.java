/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.himura.videogamesdb.dao;

import com.himura.videogamesdb.dto.PlatformDTO;
import com.himura.videogamesdb.exceptions.VgAuthException;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import java.util.List;

/**
 * Data Access Object for Platform
 * @author Cesar Himura
 */
public interface PlatformDAO {
    
    /**
     * Create a platform in the catalog
     * @param dto PlatformDTO 
     * @throws VgAuthException 
     */
    public void create(PlatformDTO dto) throws VgBadRequestException;
    
    /**
     * Find a platform by name
     * @param name Name of the platform
     * @throws VgAuthException 
     */
    public Integer getCountByName(String name);
    
    /**
     * Get all the platforms
     * @return
     * @throws VgResourceNotFoundException 
     */
    public List<PlatformDTO> findAll() throws VgResourceNotFoundException;
}
