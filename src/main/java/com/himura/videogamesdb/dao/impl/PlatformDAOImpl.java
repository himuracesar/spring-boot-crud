/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.dao.impl;

import com.himura.videogamesdb.dao.PlatformDAO;
import com.himura.videogamesdb.dto.PlatformDTO;
import com.himura.videogamesdb.exceptions.VgBadRequestException;
import com.himura.videogamesdb.exceptions.VgResourceNotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for Platform
 * @author Cesar Himura
 */
@Repository
public class PlatformDAOImpl implements PlatformDAO {
    
    private static String SQL_CREATE = "INSERT INTO cat_platforms(ID, NAME, OWNER) VALUES(NEXTVAL('seq_cat_platforms'), ?, ?)";
    private static String SQL_COUNT_BY_NAME = "SELECT COUNT(*) FROM cat_platforms WHERE name = ?";
    private static String SQL_FIND_ALL = "SELECT * FROM cat_platforms";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Create a platform in the catalog
     * @param dto Platforms's information
     * @throws VgBadRequestException 
     */
    @Override
    public void create(PlatformDTO dto) throws VgBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, dto.getName());
               ps.setString(2, dto.getOwner());
               
               return ps;
            });
        } catch(Exception e){
            throw new VgBadRequestException("Invalid data. Can not create the platform.");
        }
    }

    /**
     * Find a platform by name
     * @param name Name of the platform
     */
    @Override
    public Integer getCountByName(String name) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_NAME, Integer.class, new Object[]{ name });
    }

    /**
     * Get all the platforms
     * @return
     * @throws VgResourceNotFoundException 
     */
    @Override
    public List<PlatformDTO> findAll() throws VgResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, platformRowMapper);      
    }
    
    /**
     * Contains the information about the user in a UserDTO. The variable transforms the information
     * from a ResultSet to UserDTO. RowMapper Interface belongs to Spring framework
     */
    private RowMapper<PlatformDTO> platformRowMapper = ((rs, rowNum) -> {
        PlatformDTO dto = new PlatformDTO();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setOwner(rs.getString("owner"));
        
        return dto;
    });
}
