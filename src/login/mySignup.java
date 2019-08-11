/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


class mySignup extends JFrame implements ActionListener{
        
        Container c;
        
        JLabel contentPane = new JLabel();
        
        
        
          
        JLabel h1 = new JLabel("Sign Up Page");
        JLabel l1 = new JLabel("Name: ");
        JLabel l2 = new JLabel("Address: ");
        JLabel l3 = new JLabel("DOB: ");
        JLabel l4 = new JLabel("Email: ");
        JLabel usn = new JLabel("Username:");
        JLabel ps = new JLabel("Password:");
        JLabel psc = new JLabel("Confirm password");
        JLabel l5 = new JLabel("Phone: ");
        JLabel l6 = new JLabel("Gender: ");
        JLabel l7 = new JLabel("Client-Server: ");
        
        
        
        
        JTextField tf1 = new JTextField();
        JTextField tf2 = new JTextField();
        JTextField tf4 = new JTextField();
        JTextField tf5 = new JTextField();
        JTextField ps1 = new JPasswordField();
        JTextField psc1 = new JPasswordField();
        JTextField tfusn = new JTextField();

    
        JRadioButton rb1 = new JRadioButton("Male");
        JRadioButton rb2 = new JRadioButton("Female");
        JRadioButton rb3 = new JRadioButton("Other");
        
        String [] cs = {"Client","Server"};
        JComboBox cbcs = new JComboBox(cs);
        
        String [] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        static String [] day = new String[32];
        static String [] year = new String[75];
        JComboBox cb2 = new JComboBox(month);
        static { for(int i=0;i<32;i++)
        {
               day[i]=(i<9)?"0"+Integer.toString(i+1):Integer.toString(i+1);
        }
       

        for(int i=0;i<=74;i++)
        {
            year[i]= Integer.toString(i + 1948);
        }
}
         JComboBox cb1 = new JComboBox(day);
         JComboBox cb3 = new JComboBox(year);
        
        JButton b1 = new JButton("Sign Up!!!");
        
        ButtonGroup bg = new ButtonGroup();
        
        Font f = new Font("TIMES NEW ROMAN",Font.BOLD,25);
        
        
mySignup(){
        c = this.getContentPane();
        c.setLayout(null);
        b1.addActionListener(this);        
       
        
        
        
        h1.setBounds(200,0,150,100);
        l1.setBounds(150,100,150,25);
        tf1.setBounds(220,100,150,25); //name   
        l2.setBounds(150,150,150,25);
        tf2.setBounds(220,150,150,25);//address
        l3.setBounds(150,200,150,25);
        cb1.setBounds(220,200,50,25);//dob
        cb2.setBounds(270,200,70,25);
        cb3.setBounds(340,200,70,25);
        l4.setBounds(150,250,150,25);
        tf4.setBounds(220,250,150,25);//email
        usn.setBounds(150,300,150,25);
        tfusn.setBounds(220,300,150,25);
        
        ps.setBounds(150,350,150,25);//password
        ps1.setBounds(220,350,150,25);
        psc.setBounds(100,400,200,25);//confirm password
        psc1.setBounds(220,400,150,25);
        l5.setBounds(150,450,150,25);
        tf5.setBounds(220,450,150,25);//phone
        l6.setBounds(150,500,150,25);
        rb1.setBounds(220,500,150,25);//gender
        rb2.setBounds(220,520,150,25);//gender
        rb3.setBounds(220,540,150,25);//gender
        l7.setBounds(130,570,150,30);
        cbcs.setBounds(220,570,150,25);
        b1.setBounds(220,600,150,30);
        
        
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        rb1.setSelected(true);    
      
    
    c.add(h1);
    c.add(l1);
    c.add(l2);
    c.add(l3);
    c.add(l4);
    c.add(l5);
    c.add(l6);
    c.add(l7);
    c.add(cb1);
    c.add(cb2);
    c.add(cb3);
    c.add(cbcs);
    c.add(tf1);
    c.add(tf2);
    c.add(tf4);
    c.add(usn);
    c.add(tfusn);
    c.add(ps);
    c.add(ps1);
    c.add(psc);
    c.add(psc1);
    c.add(tf5);
    c.add(rb1);
    c.add(rb2);
    c.add(rb3);
    c.add(b1);
    h1.setFont(f);
    bg.add(rb1);
    bg.add(rb2);
    bg.add(rb3);
    }
 static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      System.out.println("email match?: "+email.matches(regex));
      return email.matches(regex);
   }
public void actionPerformed(ActionEvent e){
    if(e.getSource() == b1){
       String name= tf1.getText();
       String address= tf2.getText();
       String date = cb1.getSelectedItem().toString()+"-"+cb2.getSelectedItem().toString()+"-"+cb3.getSelectedItem().toString();
       String email = tf4.getText();
       String username = tfusn.getText();
       String password = ps1.getText();
       String cpass = psc1.getText();
       String phone = tf5.getText();
       String gender=rb1.isSelected()? "Male":(rb2.isSelected()? "Female":"Other");
       String csoption = cbcs.getSelectedItem().toString();
       Boolean flag1 = false;
       Boolean flag2 = false;
       try{
            Class.forName("com.mysql.jdbc.Driver");
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/client?useSSL=false","root","");
            PreparedStatement pst=null;
            String newsql = "SELECT username, email FROM registration";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(newsql);
            while(rs.next()){
                 //int id = rs.getInt("id");
                 String checkusn = rs.getString("username");
                 String checkemail = rs.getString("email");
       //                 JOptionPane.showMessageDialog(null, "username: "+ username+ " password: "+password);
               
               if(tfusn.getText().equals(checkusn)){
                    flag1 = true;
                    //usname = username;
                 }
               else if(tf4.getText().equals(checkemail)){
                    flag2 = true;
                    //usname = username;
                 }
                 
             }
             
             
             
            if(name.equals("")||address.equals("")||date.equals("")||email.equals("")||username.equals("")||password.equals("")||cpass.equals("")||phone.equals(""))
                JOptionPane.showMessageDialog(null,"Please enter all the fields");
            
            else if(flag1==true)
                 JOptionPane.showMessageDialog(null, "Username already taken. Please choose another username.");
            
            else if(flag2==true)
                 JOptionPane.showMessageDialog(null, "Email already taken. Please choose another email.");
            
            else if(!ps1.getText().equals(psc1.getText()))
                JOptionPane.showMessageDialog(null, "You password do not match. Please try again.");
            
            else if(isValid(tf4.getText())== false){
                JOptionPane.showMessageDialog(null, "Enter a Valid email address");
            }
            else{
//                 String sql = "INSERT INTO client.registration VALUES "
//                         + "(NULL, '"+name+"', '"+address+"', '"+date+"', '"+email+"', '"+username+"','"+password+"', '"+phone+"', '"+gender+"');";
                String sql = "INSERT INTO registration (name, address, date, email, username, password, phone, gender, CS) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, tf1.getText());
                pst.setString(2, tf2.getText());
                pst.setString(3, date);
                pst.setString(4, tf4.getText()); 
                pst.setString(5, tfusn.getText());
                pst.setString(6, ps1.getText());
                pst.setString(7, tf5.getText());
                pst.setString(8, gender);
                pst.setString(9, csoption);
          
                Boolean fire =pst.execute();
                
              // int fire = st.executeUpdate(sql);
                if(fire = true){
                    JOptionPane.showMessageDialog(null,"Account Created");
                    tf1.setText("");
                    tf2.setText("");
                    tf4.setText("");
                    tf5.setText("");
                    tfusn.setText("");
                    ps1.setText("");
                    psc1.setText("");
                    
                }
                    
//                while(rs.next()){
//                 //int id = rs.getInt("id");
//                 String username = rs.getString("username");
//                 String pass= rs.getString("password");
//                    System.out.println("username: "+username+" "+pass);
//                }
            }
                
          flag1 = false;
          flag2 = false;
          
           
       }
       
       catch (ClassNotFoundException ex) {
            Logger.getLogger(mySignup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mySignup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
}

        
}