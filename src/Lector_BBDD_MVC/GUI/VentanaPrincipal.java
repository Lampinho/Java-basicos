/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector_BBDD_MVC.GUI;

import Lector_BBDD_MVC.Controller.ControladorMenu;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Lector_BBDD_MVC.Model.ConsultaTablas;
import java.awt.event.WindowEvent;

/**
 *
 * @author Victor
 */
public class VentanaPrincipal extends JFrame{
    
    private JPanel laminaPrincipal;
    
    private JComboBox menuDesplegable;
    
    private JTextArea areaTexto;
    
    public VentanaPrincipal() throws HeadlessException {
    
        setBounds(450, 450, 600, 500);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       laminaPrincipal = new JPanel(new BorderLayout());
        menuDesplegable = new JComboBox();       
        areaTexto = new JTextArea();
        
        addWindowListener(new ControladorMenu(this));
        
        
        
        laminaPrincipal.add(menuDesplegable, BorderLayout.NORTH);
        laminaPrincipal.add(areaTexto, BorderLayout.CENTER);
        
        
        
        add(laminaPrincipal);
        
        menuDesplegable.addActionListener(new ConsultaTablas(this));
        setVisible(true);
    }

    public JComboBox getMenuDesplegable() {
        return menuDesplegable;
    }

    public void setMenuDesplegable(JComboBox menuDesplegable) {
        this.menuDesplegable = menuDesplegable;
    }

    public JTextArea getAreaTexto() {
        return areaTexto;
    }

    public void setAreaTexto(JTextArea areaTexto) {
        this.areaTexto = areaTexto;
    }
    
    
    
    
    
    
    
    
    
}
