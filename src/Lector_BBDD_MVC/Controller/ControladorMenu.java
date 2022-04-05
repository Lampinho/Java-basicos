/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector_BBDD_MVC.Controller;

import Lector_BBDD_MVC.Model.ConsultaMenu;
import javax.swing.JComboBox;
import Lector_BBDD_MVC.GUI.VentanaPrincipal;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 *
 * @author Victor
 */
public class ControladorMenu extends WindowAdapter{
    
    private ResultSet tabla = null;
    
    private VentanaPrincipal ventana;
    
    private JComboBox menu = null;
    
    private ConsultaMenu consultaMenu;
    
    //ConsultaTablas consultaTablas = null;
    
    public ControladorMenu(VentanaPrincipal ventana) {
        
        this.ventana = ventana;
        
    }
   
    public void windowOpened(WindowEvent e) {
        
        super.windowGainedFocus(e); 
        
        consultaMenu = new ConsultaMenu(); 
        
        menu = ventana.getMenuDesplegable();
        
        menu.removeAllItems();
        
        try {            
            
            tabla = consultaMenu.getRSt();
            String item ="";
            
            while (tabla.next()) {
                
                item = tabla.getString("TABLE_NAME");
                System.out.println(item);
                menu.addItem(item);
                
            }
            
        ventana.setMenuDesplegable(menu);
            
        } catch (SQLException ex) {
            
            System.err.println("fallo en controlador Menu");            
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }                       
    }  
}

