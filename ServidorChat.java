
package sockets;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class ServidorChat {

    public static void main(String[] args) {
        
        MarcoServidorChat mimarco = new MarcoServidorChat();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}

class MarcoServidorChat extends JFrame implements Runnable, Serializable {
    
    private JTextArea areatexto;
    
    HashMap<String , UsuarioChat> mapUsuariosChat;

    public MarcoServidorChat() {

        setBounds(1200, 300, 280, 350);
        JPanel milamina = new JPanel();
        milamina.setLayout(new BorderLayout());
        areatexto = new JTextArea();
        milamina.add(areatexto, BorderLayout.CENTER);
        add(milamina);
        setVisible(true);

        Thread miHilo = new Thread(this);

        miHilo.start();

    }//Interfaz e hilo de ejecucion

    public void run() { //Desarrollamos el codigo del hilo
        
        String nick, ip, mensaje; // variables con las que trabajaremos
        EnvioPaqueteDatos paqueteRecibido; //Objeto que recibimos y enviamos del cliente
        mapUsuariosChat = new HashMap<String, UsuarioChat>();

        try {

            ServerSocket miServerSocket = new ServerSocket(9999); //Abrimos el puerto de escucha: instanciamos servidor

            while (!Thread.currentThread().isInterrupted()) { // Bucle de escucucha

                Socket miSocket = miServerSocket.accept(); // Creamos socket con cliente: Aceptamos conexion con servidor
                
                // instanciamos flujo entrada
                ObjectInputStream flujoEntrada = new ObjectInputStream(miSocket.getInputStream()); 

                // Leemos objeto serializado y casteamos a la clase EnvioPaqueteDatos
                paqueteRecibido = (EnvioPaqueteDatos) flujoEntrada.readObject();

                //Obtenemos las variables del Objeto
                nick = paqueteRecibido.getNick();
                ip = paqueteRecibido.getIp();
                mensaje = paqueteRecibido.getTextoCliente();
                
                //Comprobamos si el mensaje es un inicio de conexion
                if(!mensaje.equals("aaa.bbb.ccc.ddd:Online")){

                    areatexto.append("\n" + ip + " - " + nick + ": " + mensaje); //Adjuntamos info al logger

//*************************Reenvio mensaje Cliente a usuario especificado por ip*********                

                    Socket reenvioMensaje = new Socket(ip, 9090); //instanciamos socket de salida a la IP que viene en el objeto entrante

                    ObjectOutputStream paqueteEnvio = new ObjectOutputStream(reenvioMensaje.getOutputStream()); //Creamos un flujo de salida en el socket de salida
                    
                    paqueteRecibido.setMapUsuariosChat(mapUsuariosChat);

                    paqueteEnvio.writeObject(paqueteRecibido); // Escribimos el objeto en el flujo de salida

                    reenvioMensaje.close();

                    miSocket.close(); //cerramos el socket

//*****************************Nueva conexion**********************************************                
                }else{

                    InetAddress dirClientes = miSocket.getInetAddress(); //Direccion cliente en formato inetAddres
                    String ipClientesConectados = dirClientes.getHostAddress(); //Convertimos la direccion a String                    
                    areatexto.append("\n" + ipClientesConectados + " conectado"); //Adjuntamos info al logger                    
                    System.out.println(ipClientesConectados);                                       
                    mapUsuariosChat.put(("" + UsuarioChat.numeroUsuario), new UsuarioChat(nick, ipClientesConectados));          
                    miSocket.close(); //cerramos el socket
                    System.out.println(mapUsuariosChat);    
                    
                    for (Map.Entry<String, UsuarioChat> entry : mapUsuariosChat.entrySet()) {
                        String key = entry.getKey();
                        UsuarioChat value = entry.getValue();                        
                    
                        Socket reenvioMensaje = new Socket(entry.getValue().getIpUsuario(), 9090); //instanciamos socket de salida a la IP que viene en el objeto entrante

                        ObjectOutputStream paqueteEnvio = new ObjectOutputStream(reenvioMensaje.getOutputStream()); //Creamos un flujo de salida en el socket de salida

                        paqueteRecibido.setMapUsuariosChat(mapUsuariosChat);

                        paqueteEnvio.writeObject(paqueteRecibido); // Escribimos el objeto en el flujo de salida

                        reenvioMensaje.close();

                    }
                
                }
                
            }

        } catch (IOException ex) {
            
            Logger.getLogger(LaminaClienteChat.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(MarcoServidorChat.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}

class UsuarioChat implements Serializable{
    private String nombre;
    private String ipUsuario;
    static int numeroUsuario = 0;

    public UsuarioChat(String nombre, String ipUsuario) {
        this.nombre = nombre;
        this.ipUsuario = ipUsuario;
        numeroUsuario++;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

    public static int getNumeroUsuario() {
        return numeroUsuario;
    }

    public static void setNumeroUsuario(int numeroUsuario) {
        UsuarioChat.numeroUsuario = numeroUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioChat{" + "nombre=" + nombre + ", ipUsuario=" + ipUsuario + '}';
    }
        
}
