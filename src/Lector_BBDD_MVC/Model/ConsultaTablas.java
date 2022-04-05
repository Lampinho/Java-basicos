/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector_BBDD_MVC.Model;

import Lector_BBDD_MVC.GUI.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class ConsultaTablas implements ActionListener{
    private final VentanaPrincipal ventana;
    private String tabla;
    private final JComboBox menu;
    private final JTextArea areaTexto;
    private ResultSet RSt;

    public ConsultaTablas(VentanaPrincipal ventana) {
        this.ventana = ventana;
        menu = ventana.getMenuDesplegable();
        areaTexto = ventana.getAreaTexto();
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        tabla = menu.getSelectedItem().toString();
        
        try {
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            Connection con = conexionBBDD.getConexion();
            
            String sql = "SELECT * FROM " + tabla;
            
            PreparedStatement PSt = con.prepareStatement(sql);
            
            //PSt.setString(1, tabla);
            
            RSt = PSt.executeQuery();
            
            areaTexto.setText("");
            
            while (RSt.next()) {  
                
                for (int i = 1; i <= RSt.getMetaData().getColumnCount(); i++) {
                    
                    areaTexto.append(RSt.getString(i) + " ");
                    
                }
                
                areaTexto.append("\n" );
               
            }
        
            RSt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaTablas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
    
 