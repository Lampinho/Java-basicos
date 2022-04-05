/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingGUI.ejerciciosDos;

import java.awt.BorderLayout;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class tercerEditor {
    public static void main(String[] args) {
        VentanaTercerEditor ventanaTercerEditor = new  VentanaTercerEditor();
        ventanaTercerEditor.setVisible(true);
    }    
}

class VentanaTercerEditor extends JFrame{ //Interfaz grafica 3 botones y un scroll de texto
    
    JTextPane paneltexto;

    public VentanaTercerEditor() throws HeadlessException {
        
        JPanel lamina = new JPanel();
        JPanel grupoSur = new JPanel();        
        JButton botonEscribir = new JButton("Escribir");
        JButton botonBorrar = new JButton("Borrar");
        JButton botonLeer = new JButton("Lectura");
        paneltexto = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(paneltexto);
        
        botonBorrar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                paneltexto.setText("");
            }
        }); //Limpia area de texto
        botonLeer.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                try {
                    paneltexto.setText(new LecturaArchivos().LeerArchivo());
                } catch (IOException ex) {
                    Logger.getLogger(VentanaTercerEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        botonEscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EscrituraArchivos escriba = new EscrituraArchivos(); 
                escriba.EscribirArchivos(paneltexto.getText());
            }
        });
        
        setBounds(50, 50, 550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               
        lamina.setLayout(new BorderLayout(5, 5));
        
        grupoSur.add(botonLeer);
        grupoSur.add(botonBorrar);
        grupoSur.add(botonEscribir);          
        lamina.add(scrollPane , BorderLayout.CENTER);
        lamina.add(grupoSur, BorderLayout.SOUTH);
        lamina.add(new JPanel(), BorderLayout.NORTH);
        add(lamina);
        
        
    }    
}

class LecturaArchivos{
    public String LeerArchivo() throws IOException{
        
        String cadenaTexto = "";
        try (FileReader lectorArchivo = new FileReader("C:\\Users\\Victor\\OneDrive\\Desktop\\java\\9 streams\\primerfichero.txt");){
            
            int caracter = lectorArchivo.read();           
           
            while(caracter != (-1)){
                cadenaTexto += (char)caracter;
                caracter = lectorArchivo.read(); 
            }
            
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(LecturaArchivos.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        return cadenaTexto;
    }    
}

class EscrituraArchivos{
    
    
    public void EscribirArchivos(String txt){
        
        try (FileWriter escritor = new FileWriter("C:\\Users\\Victor\\OneDrive\\Desktop\\java\\9 streams\\primerfichero.txt", true)){
            escritor.write(txt);
            
            
        } catch (IOException ex) {
            Logger.getLogger(EscrituraArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

