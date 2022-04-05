/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaEmergente;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class VentanaEmergente {
    static double dim1,dim2, dim3=0;

    public static void setDim(double ancho, double alto, double padding) {
        VentanaEmergente.dim1 = ancho;
        VentanaEmergente.dim2 = alto;
        VentanaEmergente.dim3= padding;
    }
        
    public static void mostrar(String msg, String titulo){
        
        Stage mistage = new Stage();
        mistage.initModality(Modality.APPLICATION_MODAL); // Ventana mopdal, nunca en segundo plano
        mistage.setTitle(titulo);
        mistage.setMinWidth(250);
        
        Label etiqueta = new Label();        
        etiqueta.setText(msg);
        
        Button miboton = new Button();
        miboton.setText("Cerrar");
        
        miboton.setOnAction(e -> mistage.close());
        
        BorderPane miPane = new BorderPane();
        
        Scene miescena;
        if (dim1!=0) {
            miescena = new Scene(miPane, dim1, dim2);
            miPane.setPadding(new Insets(dim3));
            dim1=0;
            dim2=0;
            dim3=0;
        }else{miescena = new Scene(miPane);}           
        
        miPane.setTop(etiqueta);
        miPane.setCenter(miboton);
                
        mistage.setScene(miescena);
        
        mistage.showAndWait();//Espea a que ocurra un evento para poder desaparecer
        
    }
    
    public static void mostrar(String msg, String titulo, double ancho, double alto, double padding){
        VentanaEmergente.dim1 = ancho;
        VentanaEmergente.dim2 = alto;
        VentanaEmergente.dim3= padding;
        
        Stage mistage = new Stage();
        mistage.initModality(Modality.APPLICATION_MODAL); // Ventana mopdal, nunca en segundo plano
        mistage.setTitle(titulo);
        mistage.setMinWidth(250);
        
        Label etiqueta = new Label();        
        etiqueta.setText(msg);
        
        Button miboton = new Button();
        miboton.setText("Cerrar");
        
        miboton.setOnAction(e -> mistage.close());
        
        BorderPane miPane = new BorderPane();
        
        
        Scene miescena;
        if (dim1!=0) {
            miescena = new Scene(miPane, dim1, dim2);
            miPane.setPadding(new Insets(dim3));
            dim1=0;
            dim2=0;
            dim3=0;
        }else{miescena = new Scene(miPane);}           
        
        miPane.setTop(etiqueta);
        miPane.setCenter(miboton);
                
        mistage.setScene(miescena);
        
        mistage.showAndWait();//Espea a que ocurra un evento para poder desaparecer
        
        
    }
}
