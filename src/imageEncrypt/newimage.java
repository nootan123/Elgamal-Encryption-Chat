/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageEncrypt;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class newimage {

  public  int random(int high, int low) {
        int q;
        Random rand = new Random();
        q = rand.nextInt(high - low) + low;
//        System.out.println(q);
        return (q);
    }

    public int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        } else if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

  public  int gen_key(int q) {
        int key;

        key = random(9999, q);
        while (gcd(q, key) != 1) {
            key = random(9999, q);
        }
        return key;
    }

   public int power(int a, int b, int c) {
         if (a == 0)
        return 0;
    if(b==0)
        return 1;
    
    long y;
    if(b%2==0){
        y = power(a,b/2,c);
        y = (y*y)%c;
    }
    else{
        y = a%c;
        y=(y*power(a,b-1,c)%c)%c;
    }
    return (int)(y+c)%c;
    
    }
  int s=1;
    public int encrypt(String name, int q, int h, int g) throws IOException {
        int k = gen_key(q);
        
        while(s==1){
        s = power(h, k, q);
        }
        System.out.println("smultiply: "+s);
        int p = power(g, k, q);
        int height, width;
        
        BufferedImage image;
        File f=null;
        f = (new File(name));
        System.out.println("f: "+f);
        image = ImageIO.read(f);
        height = image.getHeight();
        width = image.getWidth();
        FileWriter fw = new FileWriter("E:\\encrypt.jpg");
        
//        fw.write(s+"n"+"7873");
//        fw.write(System.getProperty("line.separator"));
        fw.write(height+"n"+width);
        fw.write(System.getProperty("line.separator"));
        fw.write(p+"n"+q);
        fw.write(System.getProperty("line.separator"));
        
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
//                colors[x][y] = new Color(image.getRGB(x, y));

                Color mycolor = new Color(image.getRGB(x, y));
                int alpha = (mycolor.getAlpha() * s);
                int red = (mycolor.getRed() * s);
                int green = (mycolor.getGreen() * s);
                int blue = (mycolor.getBlue() * s);
//                Color c = new Color(alpha, red, green, blue);
//                image.setRGB(x, y, c.getRGB());
                
                fw.write(alpha + "n" + red + "n" + green + "n" + blue);

                fw.write(System.getProperty("line.separator"));
            }

        }
        fw.close();
//        f = new File("E:\\encrypt.jpg");
//        ImageIO.write(image, "jpg", f);

        return p;
    }

   public void decrypt(String name,int key) throws IOException {
       int height = 0,width = 0; 
       
        
       
        
        BufferedReader fr =new BufferedReader( new FileReader(name));
        String[] lineVector;
	String line;
        int a=0;
        int x=0,y=0,p,q;
        int rf=0,gf=0,bf=0;
        double r,g,b;
        
//        line = fr.readLine();
//        lineVector = line.split("n");
//        int s = (int) Integer.parseInt(lineVector[0]);
        line = fr.readLine();
        lineVector = line.split("n");
        height = (int) Integer.parseInt(lineVector[0]);
        width = (int) Integer.parseInt(lineVector[1]);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        File f = null;
        
        line = fr.readLine();
        lineVector = line.split("n");
        p = (int) Integer.parseInt(lineVector[0]);
        q = (int) Integer.parseInt(lineVector[1]);
        int h = power(p, key, q);
        System.out.println("hdivide: "+h );
        System.out.println("key: "+key);
        System.out.println("p: "+p);
        System.out.println("q: "+q);
        Float fl = new Float("20.75f");
        while ((line = fr.readLine())!=null) {
                lineVector = line.split("n");
                r = fl.parseFloat(lineVector[1])/h; 
                g = fl.parseFloat(lineVector[2])/h;
                b = fl.parseFloat(lineVector[3])/h;
                double dr = r-Math.floor(r);
                double dg = g-Math.floor(g);
                double db = b-Math.floor(b);
//                System.out.println("d: "+dr+"   "+dg+"    "+db);
                if(dr==0.0&&dg==0.0&&db==0.0){
                    rf= (int) r;
                    gf =(int) g;
                    bf = (int) b;  
                    Color c = new Color(rf,gf,bf);
//                 System.out.println(c);
                img.setRGB(x, y, c.getRGB());
                y++;
                if(y==height){y=0;x++;}
                if (x==width){x=0;y++;}
                }
                else{
                    throw new ArithmeticException("Invalid key");
                }
                 
         }
         fr.close();
               
       f = new File("E:\\decrypt.jpg");
       ImageIO.write(img, "jpg", f);
        
        
        
   
    }

//    public static void main(String[] args) throws IOException {
////        Color[][] colors = loadPixelsFromFile(new File("E:\\farm.jpg"));
//        newimage i = new newimage();
//        int q= i.random(9999,22);
//        int g =i.random(9999,2);
//        int key = i.gen_key(q);
//        int h = i.power(g, key, q);
////        int p = i.encrypt(q, h, g);
////        i.decrypt(p, key, q);
//    }
}
