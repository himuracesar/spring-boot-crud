/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.services.impl;

import com.himura.videogamesdb.dao.UserDAO;
import com.himura.videogamesdb.dto.UserDTO;
import com.himura.videogamesdb.exceptions.VgAuthException;
import com.himura.videogamesdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Services for users objects
 * @author Cesar Himura
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;
    
    /**
     * Create a user
     * @param dto Information of the user
     * @throws VgAuthException 
     */
    @Override
    public void create(UserDTO dto) throws VgAuthException {
        int count = userDao.getCountByUsername(dto.getUsername());
        
        if(count == 0)
            userDao.create(dto);
        else
            throw new VgAuthException("The username already exists.");
    }

    /**
     * Validate the user
     * @param dto Data of the user
     * @throws VgAuthException 
     */
    @Override
    public UserDTO validate(UserDTO dto) throws VgAuthException {
        return userDao.findByUsernameAndPassword(dto);
    }
}
