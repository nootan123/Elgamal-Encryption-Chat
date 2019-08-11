/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import decrypt.decrypt;
import encrypt.encrypt;
import generator.generator;
import login.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;

public class Client extends JFrame implements ActionListener,KeyListener {
   private ObjectOutputStream output;
    private ObjectInputStream input;
    public static int linecount = 0;
    private String message="";
    private String serverIP;
    private Socket connection;
    private int port = 4;
    
    
    Container c;
   JLabel des = new JLabel("Enter your text here ");
   JTextField messin = new JTextField();
   JButton encry = new JButton("Encryption");
   JButton send = new JButton("Send");
   
   JLabel net = new JLabel("...");
   TextArea chatArea = new TextArea();
   
   JCheckBox decide = new JCheckBox();
   JLabel usen = new JLabel("Use Encryption");
   JButton decr = new JButton("Decryption");
public Client(String s){

        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100,50,600,600);
        this.setTitle(login.Login.usname);
        this.setVisible(true);
        serverIP = s;
    
    c = this.getContentPane();
    c.setLayout(null);
    c.setBackground(new Color(0,204,51));
    
    
    
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
    
}
generator g = new generator();
    encrypt e = new encrypt();
    Integer[] F;
    int qs = 0, gas = 0;
    int hs,k ;
    int q=0,ga=0,h=0;

public void keyPressed(KeyEvent ke){
    if(ke.getKeyCode()==KeyEvent.VK_ENTER)        
        send.doClick();
} 
    
    public void keyReleased(KeyEvent ke){}

   
    public void keyTyped(KeyEvent ke){}
static int idec =0;
public void actionPerformed(ActionEvent ev){
    
if(ev.getSource()== send){
        sendMessage(messin.getText());
        messin.setText("");
        System.out.println(chatArea.getText());
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
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
            if(mesLine[i].substring(0,6).equals("Server")){
                
                int in = mesLine[i].indexOf("a");
            String pe = mesLine[i].substring(7,in);
           BigDecimal p = new BigDecimal(pe);
            System.out.println("p: "+p);
            String ens = mesLine[i].substring(in+1,mesLine[i].length());
            BigDecimal en = new BigDecimal(ens);

            decryption = decrypt.decrypt(en,p,key);
            System.out.println("en: "+en+" p: "+p+" a: "+key);
            shMessage [i] = "Server - "+decryption;
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
}
}


        
        
        
        
        
    public void startRunning()
    {
        Thread T1 = new Thread(()->{
         try
       {
            net.setText("Attempting Connection ...");
            try
            {
                connection = new Socket(InetAddress.getByName(serverIP),port);
            }catch(IOException ioEception)
            {
                    JOptionPane.showMessageDialog(null,"Server Might Be Down!","Warning",JOptionPane.WARNING_MESSAGE);
            }
            net.setText("Connected to: " + connection.getInetAddress().getHostName());


            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());

            whileChatting();
       }
       catch(IOException ioException)
       {
            ioException.printStackTrace();
       }
        });
      T1.start();
    }
    
    private void whileChatting() throws IOException
    {
      messin.setEditable(true);
      do{
              try
              {
                      message = (String) input.readObject();
                      if(message.charAt(0)=='a'){
                          System.out.println("key aayo");
                          System.out.println("message: "+message);
                          String words [] = message.split("a",0);
                          System.out.println("word len: "+words.length);
                          for(int i =1;i<words.length;i++){
                            System.out.println("w["+i+"]: "+words[i]);
                          }
                          q = Integer.parseInt(words[1]);
                          ga = Integer.parseInt(words[2]);
                          h = Integer.parseInt(words[3]);
                          
                      }
                      
//                     else if(!decide.isSelected()){
//                      chatArea.append(message+"\n");
//                      linecount++;
//                      
//                     }
                      else if(!decide.isSelected()){
                      chatArea.append(message+"\n");
                      String s = chatArea.getText();
                      String [] mesLine = s.split("\\n");
                      idec = mesLine.length+1;
                      }
                      else
                          chatArea.append(message+"\n");
              }
              catch(ClassNotFoundException classNotFoundException)
              {
              }
      }while(!message.equals("Client - END"));
    }
  
    
    private void sendMessage(String message)
    {   
        chatArea.append("Client - "+message+"\n");
        try
        {
            String msgo="";
          if(decide.isSelected()) { 
        BigDecimal[] en;
        System.out.println("h = " + h);
//        F = g.printGenerators(q);
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
          }
          else{
              msgo = message;
//              String s = chatArea.getText();
//              String [] mesLine = s.split("\\n");
//              idec = mesLine.length+1;
          }
            output.writeObject("Client - " + msgo);
            output.flush();
            
            
            linecount++;
            
            
//            output.writeObject("cli - " + message);
//            output.flush();
//            chatArea.append("\nClient - "+message);
        }
        catch(IOException ioException)
        {
            chatArea.append("\n Unable to Send Message");
        }
    }



public static void main(String[] args) {
        // TODO code application logic here
        Client jf = new Client("localhost");
//        jf.setVisible(true);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setBounds(100,50,600,600);
//        
//        Container c = jf.getContentPane();
//        c.setLayout(null);
//        c.setBackground(Color.LIGHT_GRAY);
        jf.startRunning();
     }
}