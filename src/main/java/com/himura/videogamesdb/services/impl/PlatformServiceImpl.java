/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.services.impl;

import com.himura.videogamesdb.dao.PlatformDAO;
import com.himura.videogamesdb.dto.PlatformDTO;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import com.himura.videogamesdb.services.PlatformService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cesar Himura
 */
@Service
@Transactional
public class PlatformServiceImpl implements PlatformService{

    @Autowired
    private PlatformDAO platformDao;
    
    /**
     * Create a platform validating if it already exist
     * @param dto Platform's data
     * @throws VgBadRequestException 
     */
    @Override
    public void create(PlatformDTO dto) throws VgBadRequestException {
        int count = platformDao.getCountByName(dto.getName());
        
        if(count == 0)
            platformDao.create(dto);
        else
            throw new VgBadRequestException("The platform already exists.");
    }

    /**
     * Get all the platforms
     * @return
     * @throws VgResourceNotFoundException 
     */
    @Override
    public List<PlatformDTO> findAll() throws VgResourceNotFoundException {
        return platformDao.findAll();
    }
    
    
}
