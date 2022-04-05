/*
public class Socket
extends Object
implements Closeable
This class implements client sockets (also called just "sockets"). A socket is an endpoint for communication between two machines.
The actual work of the socket is performed by an instance of the SocketImpl class. An application, by changing the socket factory that creates the socket implementation, 
can configure itself to create sockets appropriate to the local firewall.

Constructor Summary
Constructors
Modifier	Constructor	Description
 	Socket​()	
Creates an unconnected socket, with the system-default type of SocketImpl.
 	Socket​(String host, int port)	
Creates a stream socket and connects it to the specified port number on the named host.
 	Socket​(String host, int port, boolean stream)	
Deprecated. 
Use DatagramSocket instead for UDP transport.
 	Socket​(String host, int port, InetAddress localAddr, int localPort)	
Creates a socket and connects it to the specified remote host on the specified remote port.
 	Socket​(InetAddress address, int port)	
Creates a stream socket and connects it to the specified port number at the specified IP address.
 	Socket​(InetAddress host, int port, boolean stream)	
Deprecated. 
Use DatagramSocket instead for UDP transport.
 	Socket​(InetAddress address, int port, InetAddress localAddr, int localPort)	
Creates a socket and connects it to the specified remote address on the specified remote port.
 	Socket​(Proxy proxy)	
Creates an unconnected socket, specifying the type of proxy, if any, that should be used regardless of any other settings.
protected	Socket​(SocketImpl impl)	
Creates an unconnected Socket with a user-specified SocketImpl.
*/

package sockets;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteChat {

	public static void main(String[] args) throws ConnectException {
            
		MarcoClienteChat mimarco=new MarcoClienteChat(); // instanciamos el marco desde el que ejecutamos todo
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
	}

}

class MarcoClienteChat extends JFrame { // Incluirá las laminas con sus elementos y gestionará la conexion con el servidor
	String nick;
   
    public MarcoClienteChat() throws ConnectException{//Constructor llama a metodo conexion e instancia lamina

            setBounds(600,300,280,350);				
            LaminaClienteChat milamina=new LaminaClienteChat();		
            add(milamina);		
            setVisible(true);	
            nick = milamina.getNick();
            conexionServidor();

    }	

    void conexionServidor() throws ConnectException{ 

        try (Socket miSocket = new Socket("192.168.0.14", 9999);){ // Socket a ip servidor

            EnvioPaqueteDatos datos = new EnvioPaqueteDatos(); // Instancia objeto paquetedatos

            datos.setTextoCliente("aaa.bbb.ccc.ddd:Online"); // Mensaje de inicio de conexion
            datos.setNick(nick);
            
            ObjectOutputStream flujoSalidaPaquete = new ObjectOutputStream(miSocket.getOutputStream()); //creamos flujo datos

            flujoSalidaPaquete.writeObject(datos); // Enviamos objeto
            
            miSocket.close(); //cerramos socket
            
            System.out.println("Solicitud conexion enviada");



        } catch (ConnectException ex) {
            System.err.println("No se ha podido conectar con el servidor");
            marcoEmergente aEmergente = new marcoEmergente();
            aEmergente.addNotify();


        } catch (IOException ex) {
            Logger.getLogger(MarcoClienteChat.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
	
}

class marcoEmergente extends JFrame{ // Ventana emergente Error de conexion
    public marcoEmergente() throws HeadlessException {
        
        setBounds(650,350,350,100);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        add(new JLabel("No se ha podido conectar con el servidor"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
}

class LaminaClienteChat extends JPanel implements Runnable{ // Lamina con la GUI y run(){}
  
//**************************BUCLE DE ESCUCHA MENSAJES**********************************************************************
    public void run() { //Definimos el codigo que se ejecutara en el hilo
        
        try {
            
            ServerSocket escuchacliente = new ServerSocket(9090); // abrimos puerto escucha            
            
            while (!Thread.currentThread().isInterrupted()) {                
            
                Socket socketentrada = escuchacliente.accept(); //Instanciamos un socket aceptando en cliente que entra por el puerto
                                
                ObjectInputStream flujoentrada = new ObjectInputStream(socketentrada.getInputStream());                
                
                EnvioPaqueteDatos paqueteEntrada = (EnvioPaqueteDatos)flujoentrada.readObject();  
                
                mapUsuariosChat = paqueteEntrada.getMapUsuariosChat();
                    
                BoxUsuarios();
                
                if(paqueteEntrada.getTextoCliente().equals("aaa.bbb.ccc.ddd:Online")){                      
                    areaChat.append("\n" + mapUsuariosChat);                    
                }else{                
                    areaChat.append("\n" + paqueteEntrada.getNick() + ": " + paqueteEntrada.getTextoCliente());
                }
                
                
                socketentrada.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LaminaClienteChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaminaClienteChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//*********************************************************************************************************************
    void BoxUsuarios(){
        
            ipBox.removeAllItems();
        
        for (Map.Entry<String, UsuarioChat> entry : mapUsuariosChat.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            
            ipBox.addItem(key + " " + entry.getValue().getNombre());
            
        }
    }
    
//************************ENVIO PAQUETE DATOS***********************************
// Constructor en donde incluimos actionPerformed del boton de envio       
    public LaminaClienteChat() { 
        
        Thread hiloentrada = new Thread(this);
        hiloentrada.start();
        
        setLayout(new BorderLayout(5, 5));        
        JPanel laminaNorte = new JPanel();
        laminaNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5 , 5));
        add(laminaNorte , BorderLayout.NORTH);
        JPanel laminasur = new JPanel();
        laminasur.setLayout(new GridLayout(2, 1));
        JPanel laminaBotones = new JPanel();
        laminaBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel nick = new JLabel();
        nick.setText(JOptionPane.showInputDialog("Intoduzca nombre usuario"));
        nickUsuario = nick.getText();
        laminaNorte.add(nick);
                
        JLabel cliente = new JLabel(" - Conectando -> ");
        laminaNorte.add(cliente);
        
        ipBox = new JComboBox();          
        laminaNorte.add(ipBox);
        
        areaChat = new JTextArea(12, 20);
        add(areaChat, BorderLayout.CENTER);
        
        JPanel laminatexto = new JPanel();       
        campo1 = new JTextField(20);
        laminatexto.add(campo1);
        laminasur.add(laminatexto);
        
//****************************ENVIO DE MENSAJE***********************************************        

        miboton = new JButton("Enviar");        
        miboton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {

                System.out.println(campo1.getText());

                try {
                    //Cremos el socket: sería la via de comunicacion

                    Socket miSocket = new Socket("192.168.0.14", 9999);
                    EnvioPaqueteDatos datos = new EnvioPaqueteDatos();
                 
                    datos.setNick(nick.getText());
                    
                    String mapkey = ipBox.getSelectedItem().toString();
                    String string = "";
                        for (int i = 0; i < mapkey.length(); i++) {
                            if(mapkey.charAt(i)== ' ') break; 
                            string += mapkey.charAt(i);
                                                   
                    }                            
                    
                    String ipEnvio = mapUsuariosChat.get(string).getIpUsuario();
                    datos.setIp(ipEnvio);
                    
                    datos.setTextoCliente(campo1.getText());
                    
                    ObjectOutputStream flujosalida = new ObjectOutputStream(miSocket.getOutputStream()); // Serializamos y enviamos
                    
                    flujosalida.writeObject(datos); // Enviamos el paquete en el flujo
                    
                    miSocket.close();
                            
                } catch (IOException ex) {
                    Logger.getLogger(LaminaClienteChat.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
//*********************************************************************************************************        
      
        laminasur.add(laminaBotones);
        laminaBotones.add(miboton);        
        add(laminasur, BorderLayout.SOUTH);
        laminasur.setPreferredSize(new Dimension(100, 75));
        
    }

    public String getNick() {
        return nickUsuario;
    }
    
    private JTextField campo1;    
    private static JComboBox ipBox;
    private String nickUsuario;
    private JButton miboton;
    private JTextArea areaChat;
    HashMap <String , UsuarioChat> mapUsuariosChat;
    
}
//Implementamos serializable ya que es necesario serializar el objeto para enviarlo por el socket
class EnvioPaqueteDatos implements Serializable{
    

    public HashMap<String, UsuarioChat> getMapUsuariosChat() {
        return mapUsuariosChat;
    }

    public void setMapUsuariosChat(HashMap<String, UsuarioChat> mapUsuariosChat) {
        this.mapUsuariosChat = mapUsuariosChat;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }   

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTextoCliente() {
        return textoCliente;
    }

    public void setTextoCliente(String textoCliente) {
        this.textoCliente = textoCliente;
    }
    
    private String nick, textoCliente, ip ;
    private HashMap<String, UsuarioChat> mapUsuariosChat; 

}