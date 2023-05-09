/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.services.impl;

import com.himura.videogamesdb.dao.VideogameDAO;
import com.himura.videogamesdb.dto.VideogameDTO;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import com.himura.videogamesdb.services.VideogameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Services for videogames
 * @author Cesar Himura
 */
@Service
@Transactional
public class VideogameServiceImpl implements VideogameService {
    
    @Autowired
    private VideogameDAO videogameDao;

     /**
     * Create a videogame record in database
     * @param dto Data for videgames
     * @throws VgBadRequestException 
     */
    @Override
    public void createVideogame(VideogameDTO dto) throws VgBadRequestException {
        int count = videogameDao.getCountByTitle(dto.getTitle());
        
        if(count == 0)
            videogameDao.createVideogame(dto);
        else
            throw new VgBadRequestException("The videogame already exists.");
    }

    /**
     * Find videogame according the filters
     * @param dto  Data for videgames
     * @return 
     */
    @Override
    public List<VideogameDTO> findVideogames(VideogameDTO dto) {
        return videogameDao.findVideogames(dto);
    }

    /**
     * Update a videgame by id
     * @param dto Videogame's data
     * @throws VgBadRequestException 
     */
    @Override
    public void updateVideogameById(VideogameDTO dto) throws VgBadRequestException {
        videogameDao.updateVideogameById(dto);
    }

    /**
     * Delete a videogame of the database
     * @param id ID of the videogame
     * @throws VgResourceNotFoundException 
     */
    @Override
    public void deleteVideogameById(int id) throws VgResourceNotFoundException {
        videogameDao.deleteVideogame(id);
    }
    
}
