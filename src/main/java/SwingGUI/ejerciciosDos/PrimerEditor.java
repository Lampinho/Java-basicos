/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingGUI.ejerciciosDos;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.StyledEditorKit;



public class PrimerEditor {
    
    public static void main(String[] args) {
        MarcoPrincipalEditor marcoPrincipalEditor = new MarcoPrincipalEditor();
        
    }
    
}
class MarcoPrincipalEditor extends JFrame{
    
    
    public MarcoPrincipalEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 600, 600);        
        add(new LaminaPrincipalEditor());
        
        setVisible(true);
    }
    
}
class LaminaPrincipalEditor extends JPanel{
        
    public LaminaPrincipalEditor(){

        setLayout(new BorderLayout());
        
        JPanel laminaMenu = new JPanel();
        
        //---------Barra Menu-----------//
        
        JMenuBar miBarra = new JMenuBar();
        
        fuente = new JMenu("Fuente");        
        estilo = new JMenu("Estilo");        
        tamano = new JMenu("Tamaño");
        
        configuraMenu("Arial", "fuente", "Arial", 1, 11);        
        configuraMenu("Courier", "fuente", "Courier", 1, 11);        
        configuraMenu("Verdana", "fuente", "Verdana", 1, 11);
        
        miBarra.add(fuente);        
        miBarra.add(estilo);        
        miBarra.add(tamano);        
               
        //---------------
       
        configuraMenu("Negrita", "estilo", "", Font.BOLD, 1);        
        configuraMenu("Cursiva", "estilo", "", Font.ITALIC, 1);
        
        configuraMenu("8", "tamaño", "", 1, 8);        
        configuraMenu("12", "tamaño", "", 1, 12);        
        configuraMenu("18", "tamaño", "", 1, 18);        
        configuraMenu("32", "tamaño", "", 1, 32);
        
        laminaMenu.add(miBarra); //La añadimos al final 
        
        add(laminaMenu, BorderLayout.NORTH);
        
        miArea = new JTextPane();
        
        add(miArea, BorderLayout.CENTER);
        
        
    }    
    
    public void configuraMenu(String rotulo, String menu, String tipoletra, int estilos, int tamanos){
        JMenuItem elemMenu = new JMenuItem(rotulo);
        
        if(menu=="fuente"){ 
            fuente.add(elemMenu);
            elemMenu.addActionListener( new StyledEditorKit.FontFamilyAction("cambiaLetra", tipoletra));
            
        }
        
        else if(menu=="estilo"){ 
            estilo.add(elemMenu);
            if(estilos == Font.BOLD) elemMenu.addActionListener(new StyledEditorKit.BoldAction());
            else elemMenu.addActionListener(new StyledEditorKit.ItalicAction());
        
        } 
        else if(menu=="tamaño"){ 
            tamano.add(elemMenu);
            elemMenu.addActionListener(new StyledEditorKit.FontSizeAction("cambioTamaño", tamanos));
        
        }
        
        //elemMenu.addActionListener(new GestionaEventos(rotulo, tipoletra, estilos, tamanos));
        
    }
    
   /* private class GestionaEventos implements ActionListener{
            
        public GestionaEventos(String rotulo, String tipoletra, int estilos, int tamanos) {
            this.estilos = estilos;
            this.rotulo = rotulo;
            this.tamanos = tamanos;
            this.tipoletra = tipoletra;
            
        }
        
        public void actionPerformed(ActionEvent e) {
            
            letraexistente = miArea.getFont();
            
            if(rotulo == "Arial" || rotulo=="Courier" || rotulo == "Verdana"){
                estilos = letraexistente.getStyle();
                tamanos = letraexistente.getSize();                
            } 
            else if(rotulo == "Negrita" || rotulo== "Cursiva" ){
                if(letraexistente.getStyle() == 2 || letraexistente.getStyle() == 1) estilos = 3;
                
                tipoletra = letraexistente.getFontName();
                tamanos = letraexistente.getSize();                
            }
            
            else if(rotulo == "8" || rotulo=="12" || rotulo == "18" || rotulo == "32"){
                estilos = letraexistente.getStyle();
                tipoletra = letraexistente.getFontName();                
            }
            
            miArea.setFont(new Font(tipoletra, estilos, tamanos));
            
        }
        
        String rotulo, tipoletra;        
        int estilos, tamanos;
        
        
    }    */
    
    JTextPane miArea;    
    JMenu fuente, estilo, tamano;    
    Font letraexistente;
    
        
}