/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector_BBDD_MVC.Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaMenu {
   
    private String nombreTabla;
    
    static ResultSet tablas;
    
    private Connection con = null;
      
    public ConsultaMenu(){
        
        try {
                        
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            con = conexionBBDD.getConexion();
            nombreTabla = con.getCatalog();
                        
            DatabaseMetaData metaData = con.getMetaData();
            
            String [] tipos = {"TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"};
            
            tablas = metaData.getTables(nombreTabla, null, "%", tipos);
            
            con.close();
                           
          
        } catch (SQLException ex) {
            
            System.err.println("Fallo en consulta Menu");
            Logger.getLogger(ConsultaTablas.class.getName()).log(Level.SEVERE, null, ex);         
            
        }
    
    }

    public ResultSet getRSt() {
        return tablas;
    }
    
    
    
}
