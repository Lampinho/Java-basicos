/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaEmergente;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class VentanaDialogo {
    
    public static Boolean mostrar(String msg, String titulo){
      
    //Instanciamos y configuramos Stage
        mistage = new Stage();
        mistage.initModality(Modality.APPLICATION_MODAL); // Ventana mopdal, nunca en segundo plano
        mistage.setTitle(titulo);
        mistage.setMinWidth(250);
        
    //Creacion etiqueta
        Label etiqueta = new Label();        
        etiqueta.setText(msg);
     
    //Creacion botones    
        Button miBotonSi = new Button();
        miBotonSi.setText("Si");        
        miBotonSi.setOnAction(e -> miBotonSi_click());
        
        Button miBotonNo = new Button();
        miBotonNo.setText("No");
        miBotonNo.setOnAction(ee -> miBotonNo_click());
        
    //Creamos la scene.Layout, utilizaremos cajas verticales y horizontales
       HBox hbox = new HBox(20); // Espaciado 8
       hbox.setAlignment(Pos.CENTER);//Configuramos la alineacion
       hbox.getChildren().addAll(miBotonSi, miBotonNo);//añadimos elementos
       
       VBox vbox = new VBox();
       vbox.getChildren().addAll(etiqueta, hbox);
       vbox.setMargin(hbox, new Insets(25));
       
    //Creamos la escena y le pasamos la caja padre por parametros 
        Scene miescena = new Scene(vbox);
    
    //Añadimos la escena al stage y lo hacemos visible
        mistage.setScene(miescena);
        
        mistage.showAndWait();//Espera a que ocurra un evento para poder desaparecer
        
        return botonSiPulsado;
        
    }

    private static void miBotonSi_click() {
        botonSiPulsado = true;
        mistage.close();
        
    }

    private static void miBotonNo_click() {
        botonSiPulsado = false;
        mistage.close();
        
    }
    
    private static Stage mistage;
    private static boolean botonSiPulsado;
    
}
