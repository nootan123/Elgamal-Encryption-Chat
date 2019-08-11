/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import Client.Client;
import MainServer.*;
//import static MainServer.Server.connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;





public class Login extends JFrame implements ActionListener, KeyListener{
   
    
    Container c;
    JLabel un = new JLabel("Username");
    JLabel pass = new JLabel("Password");
    JLabel out = new JLabel("");
    JLabel cs = new JLabel("Select client or server: ");
    public static String usname="";
    
    JTextField un1 = new JTextField();
    JPasswordField pass1 = new JPasswordField();
    
    JButton login = new JButton("Login");
    JButton reg = new JButton("Register");
    JButton exit = new JButton("Exit");
   
    String [] client = {"Client", "Server"};
    JComboBox cbcs = new JComboBox(client);
    
public Login(){
    setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,50,500,500);
        setTitle("Login");
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.LIGHT_GRAY);
   
    un.setBounds(50,100,80,20);
    pass.setBounds(50,160, 80, 20);
    out.setBounds(350,100,80, 20);
    
    un1.setBounds(150,100,150,25);
    pass1.setBounds(150,160, 150, 25);
    pass1.addKeyListener(this);
    cs.setBounds(20, 190, 150, 50);
    cbcs.setBounds(180,200,80,25);
    
    login.setBounds(50, 240, 100,30);
    reg.setBounds(180,  240, 100, 30);
    exit.setBounds(310, 240, 100, 30);
    
    login.addActionListener(this);
    reg.addActionListener(this);
    exit.addActionListener(this);
    
//    ProgressBar  p = new ProgressBar();
    //Timer t = new Timer();
    
    c.add(cs);
    c.add(cbcs);
    c.add(un);
    c.add(pass);
    c.add(out);
    c.add(un1);
    c.add(pass1);
    c.add(login);
    c.add(reg);
    c.add(exit);
   
}
//void runServer(){
//    MainServer ma = new MainServer();
//    ma.startRunning();
//}
Server s;
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)        
        login.doClick();
}
public void keyReleased(KeyEvent ke){}

public void keyTyped(KeyEvent ke){}

    public void actionPerformed(ActionEvent e){
//    MainServer ma = new MainServer();
//    ma.startRunning();

if(e.getSource()== login){
    String un = un1.getText();
    String pass = pass1.getText();
    String csoption = "";
    String cs = cbcs.getSelectedItem().toString();
    
    String mode = "";
    Boolean flag = false;
    //Boolean flag2 = false;
    Boolean isEmpty = true;
    if(un1.getText().equals("")||pass1.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Please enter all fields");
        isEmpty = true;
    }
    else{
        isEmpty = false;
       
   // initComponents();
     try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/client?useSSL=false","root","");
            //JOptionPane.showMessageDialog(null, "connected");
//            st.setString(1,un1.getText());
//            st.setString(2, pass1.getText());
            Statement st = cn.createStatement();
            String sql = "select username, password, CS from registration";
            ResultSet rs = st.executeQuery(sql);
             while(rs.next()){
                 //int id = rs.getInt("id");
                 
                 String username = rs.getString("username");
                 String password = rs.getString("password");
                 csoption = rs.getString("CS");
//                 JOptionPane.showMessageDialog(null, "username: "+ username+ " password: "+password);
               
               if(un1.getText().equals(username) && pass1.getText().equals(password) && cbcs.getSelectedItem().toString().equals(csoption)){
                   flag = true;
                    usname = username;
                    mode = csoption;
                   // JOptionPane.showMessageDialog(null,"username: "+usname+" cs: "+csoption+" flag: "+flag);
                   
                    
                 }

             }
             
    
    
            if(flag==true && mode.equals("Server") ){
              
                 // JOptionPane.showMessageDialog(null,"username: "+usname+" cs: "+csoption+" flag: "+flag);
                 JOptionPane.showMessageDialog(null, "Logged in as "+usname);
//                 se = new Server();
   
         
 s = new Server();
s.startRunning();

    
                  
             }
           
             else if (flag==true&&mode.equals("Client")){
                  JOptionPane.showMessageDialog(null, "Logged in as "+usname);
                   Client cl = new Client("localhost");
                   cl.startRunning();
                          }
             
             
             else if(flag==false && isEmpty == false)
                 JOptionPane.showMessageDialog(null, "Username or password is incorrect");

                
              
            }
    
     catch (ClassNotFoundException ex) {
        //Logger.getLogger(MyJButton.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Connection Failed: "+ex);

    } catch (SQLException ex) {
        //Logger.getLogger(MyJButton.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Connection Failed: "+ex);
            try {
                throw new SQLException();
            } catch (SQLException ex1) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
            }

    }    catch (InterruptedException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 flag = false;
  
}
   
}

if(e.getSource()==reg){
    mySignup ms = new mySignup();
    ms.setVisible(true);
    ms.setBounds(100,50,700,700);
    ms.setTitle("Register");
    Container cr = ms.getContentPane();
    cr.setLayout(null);
    cr.setBackground(Color.LIGHT_GRAY);
   
    
}

if(e.getSource()== exit){
    System.exit(0);
}
}

}
