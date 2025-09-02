/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.odontologia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class conexion {
    Connection con;
    
    public conexion(){
        
       try{ 
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/citas","root","");
           System.out.println("Se ha conectado a la base de datos");
         }catch(Exception e) {
           System.err.println("no se pudo conectar a la base de datos"+e);
       }
       
    
            
    }    
    
    public Connection getConnection(){
     return con;
    }
}

