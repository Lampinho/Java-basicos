/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector_BBDD_MVC.Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class InfoLogin {
    
    static String [] datosLogin = new String[3];

    public InfoLogin() {
    
        try {
            
            FileReader direccionDocumento = new FileReader("C:/Users/Victor/OneDrive/Documentos/NetBeansProjects/LectorBBDDMVC/src/datosLogin.txt");
            
            BufferedReader bufferLectura = new BufferedReader(direccionDocumento);
            
            for (int i = 0; i < datosLogin.length; i++) {
                String linea = bufferLectura.readLine();
                if(linea!=null) datosLogin[i] = linea; else datosLogin[i]="";
                                
            }
            
            bufferLectura.close();
        
        } catch (FileNotFoundException ex) {
            
            System.err.println("No se encuentra fichero");
            
        } catch (IOException ex) {
            System.err.println("fallo en buffer Lectura");
        }
       
       
    
    }

    public String[] getDatosLogin() {
        return datosLogin;
    }
    
}
