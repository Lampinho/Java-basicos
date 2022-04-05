/**
 * REGION - utilizar css
 * PRIORITY - prioridad para redimensionar elementos
 * padding, hgap, vhap, setPrefWrapLength, setSpacing... - Ubicacion elementos
 
* public class Region extends Parent
    Region is the base class for all JavaFX Node-based UI Controls, and all layout containers. 
    It is a resizable Parent node which can be styled from CSS. It can have multiple backgrounds and borders. 
    It is designed to support as much of the CSS3 specification for backgrounds and borders as is relevant to JavaFX. 
    The full specification is available at the W3C.
        
* public enum Priority extends Enum<Priority>
    Enumeration used to determine the grow (or shrink) priority of a given node's 
    layout area when its region has more (or less) space available and multiple 
    nodes are competing for that space.



 */
package PedidosRestaurante;

import VentanaEmergente.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Pedidos extends Application {
    
    Stage stage;
    
    TextField txtNombre, txtTelefono, txtDireccion;
    
    RadioButton radTamanoS, radTamanoM, radTamanoL;
    
    RadioButton radMasaFina, radMasaNormal;
    
    CheckBox cExtraMozzadella, cAceitunas, cJamon, cTernera, cAnchoas, cBacon, 
            cPeperoni, cSalsaBarbacoa;
    
    @Override
    public void start(Stage primaryStage) {
        
        stage = primaryStage;
      
    //----------------Cliente---------------------------------------    
        //Pane cabecera
        Text cabecera = new Text("Elige tu pizza!!!!");//cabecera        
        cabecera.setFont(new Font(STYLESHEET_MODENA, 20));        
        HBox paneCabecera = new HBox(cabecera);//Layout
        paneCabecera.setPadding(new Insets(10, 20, 10, 10));
        
        //Pane datos cliente
        Label lbNombre = new Label("Nombre");
        lbNombre.setPrefWidth(100);
        
        txtNombre = new TextField();
        txtNombre.setPrefColumnCount(20);
        txtNombre.setPromptText("Introduce tu nombre!");
        HBox paneNombre = new HBox(5, lbNombre,txtNombre);
        
        //Pane datos Telefono
        Label lbTelefono = new Label("Telefono");
        lbTelefono.setPrefWidth(100);
        
        txtTelefono = new TextField();
        txtTelefono.setPrefColumnCount(20);
        txtTelefono.setPromptText("Introduce tu Telefono!");
        HBox paneTelefono = new HBox(5, lbTelefono,txtTelefono);
        
        //Pane datos Direccion
        Label lbDireccion = new Label("Direccion");
        lbDireccion.setPrefWidth(100);
        
        txtDireccion = new TextField();
        txtDireccion.setPrefColumnCount(20);
        txtDireccion.setPromptText("Introduce tu Direccion!");
        HBox paneDireccion = new HBox(5, lbDireccion,txtDireccion);
                
        //Pane Cliente         
        VBox paneCliente = new VBox(10, paneCabecera, paneNombre, paneTelefono, paneDireccion );
        paneCliente.setPadding(new Insets(15));
         
        
    //-----------------PIZZA-----------------------------------------
        
    //Pane tamaños        
        Label lbTamano = new Label("Tamaño Pizza");//titulo
        radTamanoS = new RadioButton("Pequeña");
        radTamanoM = new RadioButton("Mediana");        
        radTamanoM.setSelected(true);//Seleccionado por defecto
        radTamanoL = new RadioButton("Grande");
        
        ToggleGroup grupoTamano = new ToggleGroup();//Creamos grupo
        radTamanoS.setToggleGroup(grupoTamano);
        radTamanoM.setToggleGroup(grupoTamano);
        radTamanoL.setToggleGroup(grupoTamano);
        
        VBox paneTamanos = new VBox(lbTamano, radTamanoS, radTamanoM, radTamanoL);
        paneTamanos.setSpacing(10);
        
    //Pane Masa        
        Label lbMasa = new Label("Tipo masa");//titulo
        radMasaFina = new RadioButton("Fina");
        radMasaNormal = new RadioButton("Normal");        
        radMasaNormal.setSelected(true);//Seleccionado por defecto
        
        ToggleGroup grupoMasa = new ToggleGroup();//Creamos grupo
        radMasaFina.setToggleGroup(grupoMasa);
        radMasaNormal.setToggleGroup(grupoMasa);
        
        VBox paneMasa = new VBox(lbMasa, radMasaFina, radMasaNormal);
        paneMasa.setSpacing(10);
        
    //Pane Ingredientes    
        cExtraMozzadella = new CheckBox("Extra queso");
        cAceitunas = new CheckBox("Aceitunas");
        cJamon = new CheckBox("Jamon");
        cTernera = new CheckBox("Ternera");
        cAnchoas = new CheckBox("Anchoas");
        cBacon = new CheckBox("Bacon");
        cPeperoni = new CheckBox("Peperonni");
        cSalsaBarbacoa = new CheckBox("Barbacoa");
        
        FlowPane paneIngredientes = new FlowPane(Orientation.VERTICAL, cExtraMozzadella, cAceitunas,
                cJamon, cTernera, cAnchoas, cBacon, cPeperoni, cSalsaBarbacoa);
        //paneIngredientes.setPadding(new Insets(25,0,10,0));
        
    //Metodos para ubicar los elementos DENTRO del contenedor
        paneIngredientes.setHgap(5);
        paneIngredientes.setVgap(10);
        paneIngredientes.setPrefWrapLength(100);
        
        
    //Pane Pizza        
        HBox panePizza = new HBox(10, paneTamanos, paneMasa, paneIngredientes);
        panePizza.setSpacing(20);
        panePizza.setPadding(new Insets(15));
        
    //Pane Control    
        Button bOk = new Button("OK");
        bOk.setPrefWidth(80);
        bOk.setOnAction(e->botonOK());
        
        Button bCancel = new Button("Cancel");
        bCancel.setPrefWidth(80);
        bCancel.setOnAction(e->botonCancel());
        
        Region espacioCSS = new Region();//En este contenedor podemos trabajar con CSS
        HBox paneControl = new HBox(15, espacioCSS, bOk, bCancel);        
        //paneControl.setAlignment(Pos.BOTTOM_RIGHT);
        paneControl.setPadding(new Insets(10,20,10,20));
        
    //Siempre intentara redimensionar y cuando no pueda mas irian las SOMETIMES
        HBox.setHgrow(espacioCSS, Priority.ALWAYS); 
        
                    //https://www.alfaforni.com/wp-content/uploads/2018/08/scegliere-il-forno-per-pizza.jpg
        
    //Pane Pedido    
        VBox panePedido = new VBox(paneCliente, panePizza, paneControl);
                        
        Scene scene = new Scene(panePedido, 380, 400);
                
        stage.setTitle("Crear pedido");
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
    }
   
    public static void main(String[] args) {
        launch(args);
    }
   
    private void botonOK() {
        
        String mensajeVentana ="";
       
        if(txtNombre.getText().length()==0) mensajeVentana+="Debe indicarnos el nombre del cliente\n";
                        
        if(txtTelefono.getText().length()==0){
        
            mensajeVentana+="Se requiere telefono de contacto\n";
        
        }else{
            
            try{Integer.parseInt(txtTelefono.getText());
            
            }catch (NumberFormatException e){mensajeVentana+="Formato no valido";}}
        
        if(txtDireccion.getText().length()==0) mensajeVentana+="Se requiere direccion entrega\n";
        
        if(mensajeVentana.length()==0){
            
            mensajeVentana = "Cliente:\n"
            + "\t" //Tabulamos
            + txtNombre.getText() + "\n\n"
            + "Direccion:\n"
            + "\t" + txtDireccion.getText() + "\n\n"
            + "Telefono:\n\t" + txtTelefono.getText() + "\n\n"
            + "Tamaño pizza ";
            if(radTamanoL.isSelected()) mensajeVentana +="grande \n";
            if(radTamanoM.isSelected()) mensajeVentana +="mediana \n";
            if(radTamanoS.isSelected()) mensajeVentana +="pequeña \n";
            mensajeVentana +="\n";
            mensajeVentana += "Masa ";
            if(radMasaFina.isSelected()) mensajeVentana += "fina \n";
            else mensajeVentana += "normal \n";
            String ingredientes = "";
            if(cAceitunas.isSelected()) ingredientes+="\n\tAceitunas ";
            if(cAnchoas.isSelected()) ingredientes+="\n\tAnchoas ";
            if(cBacon.isSelected()) ingredientes+="\n\tBacon ";
            if(cExtraMozzadella.isSelected()) ingredientes+="\n\tExtra Queso ";
            if(cJamon.isSelected()) ingredientes+="\n\tJamon ";
            if(cPeperoni.isSelected()) ingredientes+="\n\tPeperoni ";
            if(cSalsaBarbacoa.isSelected()) ingredientes+="\n\tSalsa Barbacoa ";
            if(cTernera.isSelected()) ingredientes+="\n\tTernera ";
            if(ingredientes.length()!=0){
                mensajeVentana+="\n"+"Ingredientes seleccionados:"+ingredientes; 
            }else{mensajeVentana+="Solo base de pizza";}            
            
        }
        
        VentanaEmergente.setDim(250, 500, 25);
        VentanaEmergente.mostrar(mensajeVentana, "Procesando Pedido");
        
    }

    private void botonCancel() {
        
        boolean cierre = VentanaDialogo.mostrar("¿Desea cancelar el pedido?", "Cancelando...");
        
        if(cierre) {
            
            System.out.println("Cierre controlado");
            stage.close();
            
        }
        
    }
    
}
