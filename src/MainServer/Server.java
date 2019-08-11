
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainServer;
import decrypt.decrypt;
import encrypt.encrypt;
import login.Login;
import generator.generator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends JFrame implements ActionListener, KeyListener {
    public static ObjectOutputStream output;
    public static ObjectInputStream input;
    public static Socket connection;
    public static int linecount=0;
    public static ServerSocket server;
    final private int totalClients = 100;
    final private int port = 4;
    
    Thread T1 ;
    Container c;
     
   JPanel myPanel = new JPanel();
   JLabel des = new JLabel("Enter your text here ");
   public JTextField messin = new JTextField();
   
   JButton encry = new JButton("Encryption");
   JButton send = new JButton("Send");
   public JLabel net = new JLabel("...");
   TextArea chatArea = new TextArea();
   JCheckBox decide = new JCheckBox();
   JLabel usen = new JLabel("Use Encryption");
   JButton decr = new JButton("Decryption");
   
public Server() {
    
    this.setTitle(login.Login.usname);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100,50,600,600);
    this.setVisible(true);
    
    c = this.getContentPane();
    c.setLayout(null);
    c.setBackground(new Color(102,102,255));
    des.setBounds(30,40,120,20);
    messin.setBounds(30,70,300,30);
    encry.setBounds(350,20,100,30);
    send.setBounds(350,70,100,30);
    net.setBounds(30,110,200,30);
    chatArea.setBounds(30,140,400,300);
    decide.setBounds(460,300,20,20);
    usen.setBounds(490,300,100,20);
    decr.setBounds(460,380,100,30);
    
    chatArea.setEditable(false);
    
    messin.addKeyListener(this);
    
    send.addActionListener(this);
    decr.addActionListener(this);
    encry.addActionListener(this);
    
    c.add(des);
    c.add(messin);
    c.add(encry);
    c.add(send);
    c.add(net);
    c.add(chatArea);
    c.add(decide);
    c.add(usen);
    c.add(decr);
    
    T1 = new Thread(()->{
        try {
            server = new ServerSocket(port, totalClients);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
   
     while(true){
            try {
                connection = server.accept();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    T1.start();
    System.out.println("Thread name: "+T1.getName());
   
    if(connection!=null){
        System.out.println("stopping Thread T1 ");
    T1.stop();
        System.out.println("stopped Thread T1 ");
    }
    
}


Thread T2;
public void startRunning() throws InterruptedException{
    
    T1.join(13000);
   
    
     T2 = new Thread (() -> {
         System.out.println("inpu; "+input);
         
         
         
         try {
             
             
             System.out.println("Connection: "+connection);
             
             
             output = new ObjectOutputStream(connection.getOutputStream());
             output.flush();
             input = new ObjectInputStream(connection.getInputStream());
             
             whileChatting();
             
         } catch (IOException ioException) {
         }
         finally{
             System.out.println("code fatgaya");
         }
    });
     T2.start();
//     public ObjectOutputStream output;
//     public ObjectInputStream input;
    
    
       
            
        System.out.println("Nootan: "+connection);
        net.setText(" Waiting for Someone to Connect...");
        net.setText(" Now Connected to " + connection.getInetAddress().getHostName());
}


generator g = new generator();
    encrypt e = new encrypt();
    Integer[] F;
    int qs = 0, gas = 0;
    int hs,k ;
    int q,ga,h;

   
public void keyPressed(KeyEvent ke){
    if(ke.getKeyCode()==KeyEvent.VK_ENTER)        
        send.doClick();
} 
    @Override
    public void keyReleased(KeyEvent ke){}

    @Override
    public void keyTyped(KeyEvent ke){}

static int idec =0;
public void actionPerformed(ActionEvent ev){
    
if(ev.getSource()== send){
        sendMessage(messin.getText());
        messin.setText("");
}

    
if(ev.getSource()== encry){
     generator g = new generator();
     qs = g.q;
     gas = g.g();
     hs = g.h();
     k = g.key();
     String pkeys = "a"+Integer.toString(qs)+"a"+Integer.toString(gas)+"a"+Integer.toString(hs);
     System.out.println("public keys: "+pkeys);
    try {
        output.writeObject(pkeys);
        output.flush();
    } catch (IOException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }
     JOptionPane.showMessageDialog(null,"Your key is: "+k);

        
}


if(ev.getSource()== decr){

    String test1= JOptionPane.showInputDialog("Please input key : ");
        int key = Integer.parseInt(test1);
        String s = chatArea.getText();
        String [] mesLine = s.split("\\n");
        System.out.println("Length: "+mesLine.length);
        String [] shMessage = new String[mesLine.length];
        int lineNo = mesLine.length;
//        if(idec < lineNo)
//        idec = lineNo-idec;
        String decryption ;
        
        if(idec>0 && idec < lineNo){
        for(int i=0;i<idec;i++){
            shMessage[i]= mesLine[i];
        }
        }
                   
            
       System.out.println("IDEC: "+idec);
           
        if(lineNo > idec){  
 
        for(int i=idec;i<mesLine.length;i++){
//            System.out.println(mesLine[i]);
//            System.out.println("sub:"+mesLine[i].substring(0,6));
//            System.out.println("idec: "+ i);
            if(mesLine[i].substring(0,6).equals("Client")){
                
                int in = mesLine[i].indexOf("a");
            String pe = mesLine[i].substring(9,in);
           BigDecimal p = new BigDecimal(pe);
            System.out.println("p: "+p);
            String ens = mesLine[i].substring(in+1,mesLine[i].length());
            BigDecimal en = new BigDecimal(ens);

            decryption = decrypt.decrypt(en,p,key);
            System.out.println("en: "+en+" p: "+p+" a: "+key);
            shMessage [i] = "Client - "+decryption;
    //        JOptionPane.showMessageDialog(null, "Decrypted message: "+decryption);

            }
            else{
                shMessage [i]= mesLine[i];
                
            }
            
           
        }
         
        }
        idec = lineNo;
  chatArea.setText("");
  for(int i=0;i<mesLine.length;i++){
      chatArea.append(shMessage[i]+"\n");
  }
      
//         for(int i = idec-1 ;i<=0;i++){
//            shMessage[i]= mesLine[i];
//        }
//        chatArea.setText("");
        
////        System.out.println("messages: "+s);
        
        //6,in-1
}
}



 private void whileChatting() throws IOException {
        String message = "";
        messin.setEditable(true);
        do {
            try {
                message = (String) input.readObject();
               if(message.charAt(0)=='a'){
                         
                          System.out.println("message: "+message);
                          String words [] = message.split("a",0);
                          
                          for(int i =1;i<words.length;i++){
                            System.out.println("w["+i+"]: "+words[i]);
                          }
                          q = Integer.parseInt(words[1]);
                          ga = Integer.parseInt(words[2]);
                          h = Integer.parseInt(words[3]);
                          
                      }
//               else if(!decide.isSelected()){
//                      chatArea.append(message+"\n");
//                      linecount++;
//                      System.out.println("line no: "+linecount);
//                     }
//               else if(!decide.isSelected()){
//                      chatArea.append(message+"\n");
////                      int lineno = chatArea.getLineCount();
//                       System.out.println("lineno: ");
//                     
//               }
               else if(!decide.isSelected()){
                      chatArea.append(message+"\n");
                      String s = chatArea.getText();
                      String [] mesLine = s.split("\\n");
                      idec = mesLine.length+1;
                      }
                      else
                          chatArea.append(message+"\n");
            } catch (ClassNotFoundException classNotFoundException) {

            }
        } while (!message.equals("Client - END"));
    }


public void sendMessage(String message) {
    chatArea.append("Server - "+message+"\n");
        try {
        String msgo="";
        if(decide.isSelected()){
        
        BigDecimal[] en;
        System.out.println("h = " + h);
//        F = g.printGenerator33s(q);
//        int k = e.selk(F, q);
//        System.out.println("K: " + k);
        int k = g.gen_key(q);
        en = e.encrypt(message,k,q, h, ga);
//        BigDecimal [] enm = new BigDecimal[en.length-2];
        BigDecimal p;
        System.out.println(en);
        p = en[0];
        msgo = p.toString()+"a";
        msgo+=en[1].toString();
            System.out.println("IDEC in sendMessage: "+idec);
        }
        else{
            msgo=message;
//            String s = chatArea.getText();
//            String [] mesLine = s.split("\\n");
//            idec = mesLine.length+1;
        }
            output.writeObject("Server-" + msgo);
            output.flush();
            
//            linecount++;
//            System.out.println("line no: "+linecount);
////            output.writeObject("Server - " + message);
////           output.flush();
////            chatArea.append("\nServer - "+message);
       
        } catch (IOException ioException) {
            chatArea.append("\n Unable to Send Message");
        }

}

public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Server jf = new Server();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(100,50,600,600);
        jf.setTitle("Server");
        Container c = jf.getContentPane();
        c.setLayout(null);
       
//        jf.startRunning();
     }

    
}