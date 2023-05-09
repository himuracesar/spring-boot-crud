/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.himura.videogamesdb.dao;

import com.himura.videogamesdb.dto.VideogameDTO;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import java.util.List;

/**
 * Data Access Object for Videogames
 * @author Cesar Himura
 */
public interface VideogameDAO {
    
    /**
     * Create a videogame record
     * @param dto Data of the videogame
     * @throws VgBadRequestException 
     */
    public void createVideogame(VideogameDTO dto) throws VgBadRequestException;
    
    /**
     * Get the number of videogames by title
     * @param title Title of the videogame
     * @return 
     */
    public int getCountByTitle(String title);
    
    /**
     * Get videogame according to the information in filters
     * @param dto Data of the videogame
     * @return
     * @throws VgResourceNotFoundException 
     */
    public List<VideogameDTO> findVideogames(VideogameDTO dto) throws VgResourceNotFoundException;
    
    /**
     * Find a videogame by ID
     * @param id ID of the videogame
     * @return
     * @throws VgResourceNotFoundException 
     */
    public VideogameDTO findVideogameById(int id) throws VgResourceNotFoundException;
    
    /**
     * Update information about a videogame
     * @param dto Data of the videogame
     * @throws VgBadRequestException 
     */
    public void updateVideogameById(VideogameDTO dto) throws VgBadRequestException;
    
    /**
     * Delete a videogame from database
     * @param id ID of the videogame
     * @throws VgResourceNotFoundException 
     */
    public void deleteVideogame(int id) throws VgResourceNotFoundException;
}
