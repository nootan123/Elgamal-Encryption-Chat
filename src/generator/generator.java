package generator;

import java.util.Random;

/**
 *
 * @author Nootan
 */

public class generator{
   public static int q;
    int key, h,g;
    public generator(){
        q = random(9999,22);
        g =random(9999,2);//
        key = gen_key(q);
        h = power(g, key, q);//
    }
public int random(int high, int low){
        int a;
        Random rand = new Random();
        a = rand.nextInt(high-low)+low;
        
        return(a);
}

public int gcd(int a, int b){
if(a<b)
    return gcd(b,a);
else if(a%b==0)
    return b;
else
    return gcd(b,a%b);
}

public int gen_key(int q){
int k;
k = random(9999,q);
while(gcd(q, k)!=1){
    k = random(9999,q);
}
return k;
}
public static int power(int a, int b, int c) {
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
    
    
    
//                int x,y;
//		x = 1;
//		y = a;
//		while (b > 0) {
//			if (b % 2 == 0)
//				x = (x*y)%c;
//			y = (y * y) % c;
//			b = (int) (b / 2);
//		}
//		return (x % c);
	}
   public int key(){return key;}
   public int h(){return h;}
   public int g(){return g;}
}