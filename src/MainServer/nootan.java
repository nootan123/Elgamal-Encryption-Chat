/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainServer;

import static MainServer.Server.connection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nootan
 */
public  class nootan implements Runnable{
    private Socket  Connection;
    private ServerSocket server;
    
     public void run() {
        
        try {
           
            connection = server.accept();
        } catch (IOException ex) {
            Logger.getLogger(nootan.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
     

     public Socket getValue() {
 
         return Connection;
     }
 }


