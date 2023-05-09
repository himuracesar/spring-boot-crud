/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.himura.videogamesdb.dao.impl;

import com.himura.videogamesdb.dao.UserDAO;
import com.himura.videogamesdb.dto.UserDTO;
import com.himura.videogamesdb.exceptions.VgAuthException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for users
 * @author Cesar Himura
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static String SQL_CREATE = "INSERT INTO tb_users(id, username, password) VALUES(NEXTVAL('seq_tb_users'), ?, ?)";
    private static String SQL_COUNT_BY_NAME = "SELECT COUNT(*) FROM tb_users WHERE username = ?";
    private static String SQL_FIND_BY_USERNAME = "SELECT * FROM tb_users WHERE username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Create a user 
     * @param dto Data of the user
     * @throws VgAuthException 
     */
    @Override
    public void create(UserDTO dto) throws VgAuthException {
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10));
        
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, dto.getUsername());
               ps.setString(2, hashedPassword);
               
               return ps;
            });
        } catch(Exception e){
            throw new VgAuthException("Invalid data. Can not create the platform.");
        }
    }

    /**
     * Get coount users by username
     * @param username Username
     * @return 
     */
    @Override
    public Integer getCountByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_NAME, Integer.class, new Object[]{ username });
    }

    /**
     * Find a user by username and password. The password is cyphered.
     * @param dto Data of the user
     * @return
     * @throws vgAuthException 
     */
    @Override
    public UserDTO findByUsernameAndPassword(UserDTO dto) throws VgAuthException {
        try{
            UserDTO userFound = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, userRowMapper, new Object[]{ dto.getUsername() });
            if(!BCrypt.checkpw(dto.getPassword(), userFound.getPassword()))
                   throw new VgAuthException("Invalid Password");
            
            return userFound;
        } catch (EmptyResultDataAccessException e){
            throw new VgAuthException("Invalid username/password");
        }
    }
    
    /**
     * Contains the information about the user in a UserDTO. The variable transforms the information
     * from a ResultSet to UserDTO. RowMapper Interface belongs to Spring framework
     */
    private RowMapper<UserDTO> userRowMapper = ((rs, rowNum) -> {
        UserDTO dto = new UserDTO();
        dto.setId(rs.getInt("id"));
        dto.setUsername(rs.getString("username"));
        dto.setPassword(rs.getString("password"));
        
        return dto;
    });
}
