package module1;
import java.util.*;
/** KMP: search emergency keywords and incident codes in reports. */
public class KMP {
    public static int[] prefix(String p){int[] pi=new int[p.length()];for(int i=1,k=0;i<p.length();i++){while(k>0&&p.charAt(i)!=p.charAt(k))k=pi[k-1];if(p.charAt(i)==p.charAt(k))k++;pi[i]=k;}return pi;}
    public static List<Integer> search(String text,String pat){List<Integer> r=new ArrayList<>();if(pat.isEmpty())return r;int[] pi=prefix(pat);for(int i=0,k=0;i<text.length();i++){while(k>0&&text.charAt(i)!=pat.charAt(k))k=pi[k-1];if(text.charAt(i)==pat.charAt(k))k++;if(k==pat.length()){r.add(i-k+1);k=pi[k-1];}}return r;}
    public static void demo(){
        String report="FIRE reported at Sector 7. Incident INC-2041 escalated. FIRE spreading; INC-2041 needs backup.";
        System.out.println("[KMP] Report: "+report);
        System.out.println("  'FIRE' at positions: "+search(report,"FIRE"));
        System.out.println("  'INC-2041' at positions: "+search(report,"INC-2041"));
    }
}
