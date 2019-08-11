package decrypt;
import java.math.BigDecimal;
import java.math.RoundingMode;
import generator.generator;
/**
 *
 * @author Nootan
 */

public class decrypt extends generator{
public static String decrypt(BigDecimal  en_msg,BigDecimal pe, int key){
    int p = pe.intValue();
    int he = power(p, key, q);
    System.out.println("power(p, key, q)="+p+", "+key+", "+q+"="+he);
    BigDecimal h = new BigDecimal(he);
    BigDecimal d;
    System.out.println("h: "+h);
   
    d = en_msg.divide(h,RoundingMode.HALF_UP);
        String de = d.toString();        
        System.out.print("Decrypted message: ");
        String sub;
        
        char as ;
        String ss="";
        for(int i=0;i<de.length();i+=4){
            sub = (String) de.subSequence(i, i+4);
           
            if(sub.charAt(3)=='1'){
                sub = sub.substring(0, 0);
               as = (char)(Integer.parseInt(sub));
               ss+=String.valueOf(as);
               
            }
           else if(sub.charAt(3)=='2'){
                sub = sub.substring(0,2);
                as = (char)(Integer.parseInt(sub));
                ss+=String.valueOf(as);
            }
           else if(sub.charAt(3)=='3'){
                sub = sub.substring(0,3);
                as = (char)(Integer.parseInt(sub));
               ss+=String.valueOf(as);
           }
           
        }
        System.out.println(ss);
        
        

return ss;
}
}