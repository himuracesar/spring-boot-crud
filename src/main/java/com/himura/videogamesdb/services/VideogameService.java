/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.himura.videogamesdb.services;

import com.himura.videogamesdb.dto.VideogameDTO;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import java.util.List;

/**
 * Services for videogames
 * @author Cesar Himura
 */
public interface VideogameService {
    
    /**
     * Create a videogame record in database
     * @param dto Data for videgames
     * @throws VgBadRequestException 
     */
    public void createVideogame(VideogameDTO dto) throws VgBadRequestException;
    
    /**
     * Find videogame according the filters
     * @param dto  Data for videgames
     * @return 
     */
    public List<VideogameDTO> findVideogames(VideogameDTO dto);
    
    /**
     * Update the information of a videogame by ID
     * @param dto Information of the videogame
     * @throws VgBadRequestException 
     */
    public void updateVideogameById(VideogameDTO dto) throws VgBadRequestException;
    
    /**
     * Delete a videogame by ID
     * @param id ID of the videogame
     * @throws VgResourceNotFoundException 
     */
    public void deleteVideogameById(int id) throws VgResourceNotFoundException;
}
