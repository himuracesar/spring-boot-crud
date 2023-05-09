/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.dao.impl;

import com.himura.videogamesdb.dto.VideogameDTO;
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
import com.himura.videogamesdb.dao.VideogameDAO;
import com.himura.videogamesdb.dto.PlatformDTO;
import java.util.ArrayList;

/**
 * Data Access Object implementation for videogames
 * @author Cesar Himura
 */
@Repository
public class VideogameDAOImpl implements VideogameDAO {

    private static String SQL_CREATE_VIDEOGAME = "INSERT INTO tb_videogames(id, title, id_platform, physics_format, digital_format, created_at) " +
                                                 "VALUES(NEXTVAL('seq_tb_videogames'), ?, ?, ?, ?, current_timestamp)";
    
    private static String SQL_COUNT_BY_TITLE = "SELECT COUNT(*) FROM tb_videogames WHERE title = ?";
    
    private static String SQL_FIND_VIDEOGAMES = "SELECT vg.id, pl.id as id_platform, pl.name as platform, vg.title, vg.physics_format, vg.digital_format, " +
                                                "vg.created_at " +
                                                "FROM tb_videogames vg, cat_platforms pl " +
                                                "WHERE vg.id_platform = pl.id AND vg.physics_format = ? AND vg.digital_format = ? ";
    
    private static String SQL_FIND_VIDEOGAME_BY_ID = "SELECT * FROM tb_videogames WHERE id = ?";
    
    private static String SQL_UPDATE_VIDEOGAME_BY_ID = "UPDATE tb_videogames SET title = ?, physics_format = ?, digital_format = ? " +
                                                       "WHERE id = ?";
    
    private static String SQL_DELETE_VIDEOGAME_BY_ID = "DELETE FROM tb_videogames WHERE id = ?";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    /**
     * Create a videogame in database
     * @param dto Information of the videogame
     * @throws VgBadRequestException 
     */
    @Override
    public void createVideogame(VideogameDTO dto) throws VgBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(SQL_CREATE_VIDEOGAME, Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, dto.getTitle());
               ps.setInt(2, dto.getPlatformDto().getId());
               ps.setInt(3, (dto.isPhysicsFormat()) ? 1 : 0);
               ps.setInt(4, (dto.isDigitalFormat()) ? 1 : 0);
               
               return ps;
            });
        } catch(Exception e){
            throw new VgBadRequestException("Invalid data. Can not create the videogame.");
        }
    }

    /**
     * Count the videogames by title
     * @param title Title of the videogame
     * @return 
     */
    @Override
    public int getCountByTitle(String title) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_TITLE, Integer.class, new Object[]{ title });
    }

    /**
     * Find the videogames according to the filters. I take advantage for Java to create a dynamic query
     * @param dto Information of the videogame
     * @return
     * @throws VgResourceNotFoundException 
     */
    @Override
    public List<VideogameDTO> findVideogames(VideogameDTO dto) throws VgResourceNotFoundException {
        List params = new ArrayList();
        params.add((dto.isPhysicsFormat()) ? 1 : 0);
        params.add((dto.isDigitalFormat()) ? 1 : 0);
        
        StringBuffer sb = new StringBuffer(SQL_FIND_VIDEOGAMES);
        
        if(dto.getTitle() != null && !"".equals(dto.getTitle())){
            sb.append("AND vg.title = ? ");
            
            params.add(dto.getTitle());
        }
        
        if(dto.getPlatformDto() != null && dto.getPlatformDto().getId() > 0){
            sb.append("AND vg.id_platform = ? ");
            
            params.add(dto.getPlatformDto().getId());
        }
        
        return jdbcTemplate.query(sb.toString(), videogameRowMapper, params.toArray()); 
        
        //return jdbcTemplate.query(SQL_FIND_VIDEOGAMES, videogameRowMapper); 
    }

    /**
     * Find a videogame by id
     * @param id ID of the videogame
     * @return
     * @throws VgResourceNotFoundException 
     */
    @Override
    public VideogameDTO findVideogameById(int id) throws VgResourceNotFoundException {
        return jdbcTemplate.queryForObject(SQL_FIND_VIDEOGAME_BY_ID, videogameRowMapper, new Object[]{ id });
    }

    /**
     * Update a videogame by ID
     * @param dto Information of the videogame
     * @throws VgBadRequestException 
     */
    @Override
    public void updateVideogameById(VideogameDTO dto) throws VgBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_VIDEOGAME_BY_ID, Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, dto.getTitle());
               ps.setInt(2, (dto.isPhysicsFormat()) ? 1 : 0);
               ps.setInt(3, (dto.isDigitalFormat()) ? 1 : 0);
               ps.setInt(4, dto.getId());
               
               return ps;
            });
        } catch(Exception e){
            throw new VgBadRequestException("Invalid data. Can not update the videogame.");
        }
    }

    /**
     * Delete a videogame from database by id
     * @param id ID of the videogame
     * @throws VgResourceNotFoundException 
     */
    @Override
    public void deleteVideogame(int id) throws VgResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_DELETE_VIDEOGAME_BY_ID, new Object[]{ id });
        if(count == 0)
            throw new VgResourceNotFoundException("Videogame not found.");
    }
    
    /**
     * Contains the information about the user in a UserDTO. The variable transforms the information
     * from a ResultSet to UserDTO. RowMapper Interface belongs to Spring framework
     */
    private RowMapper<VideogameDTO> videogameRowMapper = ((rs, rowNum) -> {
        PlatformDTO platformDto = new PlatformDTO();
        platformDto.setId(rs.getInt("id_platform"));
        platformDto.setName(rs.getString("platform"));
        
        VideogameDTO videogameDto = new VideogameDTO();
        videogameDto.setPlatformDto(platformDto);
        videogameDto.setId(rs.getInt("id"));
        videogameDto.setTitle(rs.getString("title"));
        videogameDto.setPhysicsFormat( (rs.getInt("physics_format")) == 1 ? true : false);
        videogameDto.setDigitalFormat( (rs.getInt("digital_format")) == 1 ? true : false);
        videogameDto.setCreatedAt(rs.getDate("created_at"));
        
        return videogameDto;
    });
}
