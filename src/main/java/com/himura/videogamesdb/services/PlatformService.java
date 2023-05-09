/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.himura.videogamesdb.services;

import com.himura.videogamesdb.dto.PlatformDTO;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import java.util.List;

/**
 * Services for platforms
 * @author Cesar Himura
 */
public interface PlatformService {
    
    /**
     * Create a platform validating if it already exist
     * @param dto Platform's data
     * @throws VgBadRequestException 
     */
    public void create(PlatformDTO dto) throws VgBadRequestException;
    
    /**
     * Get all the platforms
     * @return
     * @throws VgResourceNotFoundException 
     */
    public List<PlatformDTO> findAll() throws VgResourceNotFoundException;
}
