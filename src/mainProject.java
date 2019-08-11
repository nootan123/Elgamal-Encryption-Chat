        
import login.Login;
import java.awt.Color;
import imageEncrypt.Choose;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.layout.Border;
import javax.swing.*;
import javax.swing.border.LineBorder;
import login.Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nootan
 */
public class mainProject extends JFrame implements ActionListener{
    Container c;
    JButton chat = new JButton("Encrypted Chat");
    JButton image = new JButton("Image Encryption");
    mainProject(){
     c = getContentPane();
    c.setLayout(null);
    
     chat.setBounds(130,130,200,40);
     image.setBounds(130,240,200,40);
     
     chat.addActionListener(this);
     image.addActionListener(this);
     
     c.add(chat);
     c.add(image);
     
     Font f = new Font("Arial",Font.BOLD,15); 
     chat.setFont(f);
     image.setFont(f);
     
     LineBorder roundedBorder = new LineBorder(Color.BLACK, 1, true);
     chat.setBorder(roundedBorder);
     image.setBorder(roundedBorder);
     
     
     
    }
    
    public void actionPerformed(ActionEvent e){
     if(e.getSource()==chat){
     
     Login l = new Login();
        
     }
     
     if(e.getSource()==image){
        System.out.println("image button clicked!!");
        Choose c = new Choose();
     }
     }
public static void main(String[] args) {
         mainProject mp = new mainProject();
        mp.setVisible(true);
        mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp.setBounds(100,50,500,500);
        mp.setTitle("Home");
        Container c = mp.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.LIGHT_GRAY);
    }

  
   
      
}

