/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaEmergente;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * setOnAction
    public final void setOnAction​(EventHandler<ActionEvent> value)
    Sets the value of the property onAction.
 

* Interface EventHandler<T extends Event>
    Type Parameters:
    T - the event class this handler can handle
    All Superinterfaces:
    EventListener
    All Known Implementing Classes:
    WeakEventHandler

* Functional Interface:
    This is a functional interface and can therefore be used as the assignment 
    target for a lambda expression or method reference.
 
 
 */
public class CierreAplicacion extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        misStage = primaryStage;
        
        Button botonClick = new Button();        
        botonClick.setText("Pulsar");    
        /*  
        Opcion 1: Expresion Lambda:
        Ejemplo de uso de interfaz funcional. 1 unico metodo que utilizamos con una lambda
        */
        botonClick.setOnAction(e->hacerClick());        
        
        /*Opcion2: Clase interna anonima
        botonClick.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hacerClick();
            }});
        */
        
        /*
        Opcion 3: Crear una clase que implemente EventHandler y sobreescriba handle(ActionEvent e)
        */
        
        Button botonCerrar = new Button();
        botonCerrar.setText("Cerrar");
        botonCerrar.setOnAction(e->botonCerrar());
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(botonClick,botonCerrar);
        vbox.setAlignment(Pos.CENTER);
        
        Scene escena = new Scene(vbox, 350, 250);
        
        primaryStage.setScene(escena);
        primaryStage.setTitle("Contador de clicks");
        
//¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡DETENER PROPAGACION DE EVENTO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        primaryStage.setOnCloseRequest(e -> { //Evento a ejecutar en cierre ventana
    /*public void consume​()
        Marks this Event as consumed. This stops its further propagation.
            
        En este ejemplo al detener la propagacion no se cerrara la aplicacion
            y se podra ejecutar el codigo pertinente para hacer un cierre 
            o se podra cancelar el proceso.
    */
            e.consume();
            
            botonCerrar();
                });
//****************************************************************************
  
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        
        launch(args);
        
    }
    
    public void hacerClick(){
        
        contador++;
        
        VentanaEmergente.mostrar("Has realizado " + contador + "click", "Click");
        
    }
    
    public void botonCerrar(){
        
        boolean confirmar = false;
        
        confirmar = VentanaDialogo.mostrar("¿Esta seguro?", "Saliendo");
        
        if(confirmar){
            
//Irian todas aquellas tareas a realizar antes de cerrar la aplicacion 

            misStage.close();
            
        }
        
    }
    
    Stage misStage;
    
    int contador = 0;
    
}
