package module1;
import java.util.*;
/** Rabin-Karp: search supply and incident identifiers via rolling hash. */
public class RabinKarp {
    static final long B=257, M=1_000_000_007L;
    public static List<Integer> search(String t,String p){List<Integer> r=new ArrayList<>();int n=t.length(),m=p.length();if(m==0||m>n)return r;long hp=0,ht=0,pw=1;for(int i=0;i<m;i++){hp=(hp*B+p.charAt(i))%M;ht=(ht*B+t.charAt(i))%M;if(i>0)pw=pw*B%M;}
        for(int i=0;;i++){if(hp==ht&&t.regionMatches(i,p,0,m))r.add(i);if(i+m>=n)break;ht=(ht-t.charAt(i)*pw%M+M)%M;ht=(ht*B+t.charAt(i+m))%M;}return r;}
    public static void demo(){
        String log="SUP-118 water; SUP-221 meds; INC-900 flood; SUP-118 dispatched";
        System.out.println("[Rabin-Karp] Log: "+log);
        for(String id:new String[]{"SUP-118","INC-900","SUP-999"})System.out.println("  "+id+" -> "+search(log,id));
    }
}
