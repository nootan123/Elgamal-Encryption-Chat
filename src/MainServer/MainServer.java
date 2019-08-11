package MainServer;
import decrypt.decrypt;
import encrypt.encrypt;
import generator.generator;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainServer extends javax.swing.JFrame {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    private ServerSocket server;
    private int totalClients = 100;
    private int port = 6780;

    public  MainServer() {
        initComponents();
        
        this.setTitle("Server");
        this.setVisible(true);
        status.setVisible(true);
    }

    public void startRunning() {
        try {
            server = new ServerSocket(port, totalClients);
            while (true) {
                try {
                    status.setText(" Waiting for Someone to Connect...");
                    connection = server.accept();
                    status.setText(" Now Connected to " + connection.getInetAddress().getHostName());

                    output = new ObjectOutputStream(connection.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(connection.getInputStream());

                    whileChatting();

                } catch (EOFException eofException) {
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void whileChatting() throws IOException {
        String message = "";
        jTextField1.setEditable(true);
        do {
            try {
                message = (String) input.readObject();
                chatArea.setText(message);
            } catch (ClassNotFoundException classNotFoundException) {

            }
        } while (!message.equals("Client - END"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(null);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 360, 270);

        jTextField1.setText("hello");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(30, 50, 270, 30);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(310, 50, 80, 30);

        status.setText("...");
        jPanel1.add(status);
        status.setBounds(30, 80, 300, 40);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Write your text here");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 30, 150, 20);

        jButton2.setText("Encrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(310, 10, 100, 25);

        jButton3.setText("Generator");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(429, 20, 110, 25);

        jButton4.setText("Decryption");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(410, 250, 120, 25);

        jCheckBox1.setBackground(new java.awt.Color(102, 102, 255));
        jCheckBox1.setText("use encryption");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(400, 150, 130, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(572, 447));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        sendMessage(jTextField1.getText());
        jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        sendMessage(jTextField1.getText());
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed
   generator g = new generator();
    encrypt e = new encrypt();
    Integer[] F;
    int q = 0, ga = 0;
    int h ;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JTextField qField = new JTextField(5);
        JTextField hField = new JTextField(5);
        JTextField gField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("q: "));
        myPanel.add(qField);
        myPanel.add(Box.createVerticalStrut(150)); // a spacer
        myPanel.add(new JLabel("h:"));
        myPanel.add(hField);
        myPanel.add(Box.createVerticalStrut(15));
        myPanel.add(new JLabel("g:"));
        myPanel.add(gField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter q and h Values", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            q = Integer.parseInt(qField.getText());
            h = Integer.parseInt(hField.getText());
            ga = Integer.parseInt(gField.getText());
            System.out.println("ga: "+ga+" h: "+h);
        }
        

       
       
//        System.out.println("h: "+q+" g: "+g+" h: "+h);

//        int q = JOptionPane.showInputDialog();
//        int k = encrypt.selk(F[],q);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String test1= JOptionPane.showInputDialog("Please input key : ");
        int key = Integer.parseInt(test1);
        String s = chatArea.getText();
        int in = s.indexOf("a");
        
        String pe = s.substring(9,in);
       BigDecimal p = new BigDecimal(pe);
        System.out.println("p: "+p);
        String ens = s.substring(in+1,s.length());
        BigDecimal en = new BigDecimal(ens);
        String decryption;
        decryption = decrypt.decrypt(en,p,key);
        System.out.println("en: "+en+" p: "+p+" a: "+key);
//        JOptionPane.showMessageDialog(null, "Decrypted message: "+decryption);
        chatArea.setText("server-"+decryption);
        //6,in-1
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        generator g = new generator();
        System.out.println("hello world");
        JOptionPane.showMessageDialog(null,"your keys are: q: "+g.q+" g: "+g.g()+" h: "+g.h()+"\nSecret key: "+g.key());
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void sendMessage(String message) {
        try {
        String msgo="";
        if(jCheckBox1.isSelected()){
        
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
            msgo=message;
        }
            output.writeObject("Server-" + msgo);
            output.flush();
            chatArea.append("\nServer - "+msgo);
//            output.writeObject("Server - " + message);
//           output.flush();
//            chatArea.append("\nServer - "+message);
       
        } catch (IOException ioException) {
            chatArea.append("\n Unable to Send Message");
        }
    }
    
       
//        JLabel1.status.setVisible(true);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
