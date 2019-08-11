/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt;
import static java.lang.Math.pow;
import generator.generator;
import java.math.BigDecimal;

/**
 *
 * @author Nootan
 */
public class encrypt extends generator{
    
    
public BigDecimal []  encrypt(String msg,int k, int q,int h,int g){
   int ar [] = new int[msg.length()];
        for( int i=0;i<ar.length;i++){
           int no  = (int)msg.charAt(i);
           ar[i]=no;
           
        }
    System.out.println("");
    String m,mg="";
        for(int i=0;i<msg.length();i++){ 
        m = Integer.toString(ar[i]);
            switch (m.length()) {
               
                case 1:
                    m=m+"001";
                    mg+=m;
                    break;
                case 2:
                    m=m+"02";
                    mg+=m;
                    break;
                 case 3:
                    m=m+"3";
                    mg+=m;
                    break;
                default:
                    break;
            }
        }
     System.out.println(mg);
    int se = power(h, k, q);
    BigDecimal s = new BigDecimal(se);
    int pe = power(g,k, q);
    BigDecimal p = new BigDecimal(pe);
    System.out.println("s: "+s);
    BigDecimal en_msg [] = new BigDecimal[ar.length+1]; 
    en_msg[0]= p;
          en_msg[1]= s.multiply(new BigDecimal(mg));
        System.out.println("encrypted message: ");
        
            System.out.print(en_msg[1]);
    System.out.println("");
    return en_msg;
  }
}