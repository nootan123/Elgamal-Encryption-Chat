/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainServer;
import MainServer.*;
import static MainServer.Server.connection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nootan
 */
public class Listen implements Runnable{
    private Socket  Connection;
    private ServerSocket server;
     private int totalClients = 100;
    private int port = 6780;
     
     public void run() {
      
        try {
            server = new ServerSocket(port, totalClients);
        } catch (IOException ex) {
            Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Server s = new Server();
            s.net.setText(" Waiting for Someone to Connect...");
             System.out.println("Connection: "+connection);
                    s.net.setText(" Now Connected to " + connection.getInetAddress().getHostName());

                    s.output = new ObjectOutputStream(connection.getOutputStream());
                    s.output.flush();
                    s.input = new ObjectInputStream(connection.getInputStream());

//                    s.whileChatting(ObjectInputStream input);
            
        } catch (IOException ex) {
            Logger.getLogger(nootan.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
     }
 

