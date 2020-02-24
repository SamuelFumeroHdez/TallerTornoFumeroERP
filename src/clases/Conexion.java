/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.*;



public class Conexion {
    
    //Conexion Local
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/tallertornofumeroerp", "root", "");
            return cn;
        }catch(SQLException e){
            System.out.println("Error en la conexión local " + e);
        }
        
        return (null);
    }
    
}
