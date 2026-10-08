package module1;
import java.util.*;
/** Z-Function: detect repeated emergency patterns. */
public class ZFunction {
    public static int[] z(String s){int n=s.length();int[] z=new int[n];for(int i=1,l=0,r=0;i<n;i++){if(i<r)z[i]=Math.min(r-i,z[i-l]);while(i+z[i]<n&&s.charAt(z[i])==s.charAt(i+z[i]))z[i]++;if(i+z[i]>r){l=i;r=i+z[i];}}return z;}
    public static List<Integer> search(String text,String pat){String s=pat+"\u0001"+text;int[] z=z(s);List<Integer> r=new ArrayList<>();for(int i=pat.length()+1;i<s.length();i++)if(z[i]>=pat.length())r.add(i-pat.length()-1);return r;}
    /** Smallest period of signal => repeated pattern. */
    public static String smallestPeriod(String s){int[] z=z(s);int n=s.length();for(int p=1;p<n;p++)if(n%p==0&&z[p]==n-p)return s.substring(0,p);return s;}
    public static void demo(){
        String signal="SOS-SOS-SOS-SOS-";
        System.out.println("[Z-Function] Signal: "+signal);
        System.out.println("  Repeating unit: '"+smallestPeriod(signal)+"'");
        System.out.println("  'SOS' occurrences: "+search(signal,"SOS"));
    }
}
