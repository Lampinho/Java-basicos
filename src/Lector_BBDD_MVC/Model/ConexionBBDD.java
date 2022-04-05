/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector_BBDD_MVC.Model;


import Lector_BBDD_MVC.Controller.InfoLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class ConexionBBDD{
    
    Connection conexion;
    private String url;
    private String user ;
    private String psswd;

    public ConexionBBDD() {
        
        InfoLogin infoLogin = new InfoLogin();
        String [] datosconexion = infoLogin.getDatosLogin();
            url = datosconexion[0];
            user = datosconexion[1];
            psswd = datosconexion[2];
        
        try {
            
            conexion = DriverManager.getConnection(url,user,psswd);
            
        } catch (SQLException ex) {
            
            System.err.println("Fallo conexion BBDD");
            
            Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    public Connection getConexion() {
        
        return conexion;
        
    }
    
    
}
