# spring-boot-crud

This application is to manage a simple personal inventory of videogames. In this moment it is only the backend part.

The technology used is:
  - Spring Boot 
  - Java 11
  - JWT for authentification with 2 factors (token management)
  - Maven
  - PostgreSQL in database

The script for create the database is in sql folder.
  Videogames_DB.sql
  
To execute the script write in a shell:
  psql -U postgres --file Videogame_DB.sql
  
The project was worked in NetBeans 17.

How to use the application
----------------------------------------
##1. You have to crete a user with
  http://localhost:8080/api/users/create
  ```json
  {
    "username" : "admin",
    "password" : "qwerty"
  }
  ```
  
  The user will be created with the password cypher in database.
  
 ##2. Login to get a valid token
  http://localhost:8080/api/users/login
   ```json
  {
    "username" : "admin",
    "password" : "qwerty"
  }
  ```
  
  Web Service returns a token and you have to copy and send it in the header of the request to other functions like platforms and videogames.
  
  ### With fetch API for example
  ```javascript
  fetch('https://reqbin.com/echo/get/json', {
    headers: {Authorization: 'Bearer {token}'}
  })
   .then(resp => resp.json())
   .then(json => console.log(JSON.stringify(json)))
   ```
   
   The label `Authorization` is important because is validated in the filter to validate the token.
   
   The web services to create, get, update or delete videogames or create or get platforms are protected so you need the token.
    
  
