package module2;
import java.util.*;
/** Suffix Array (prefix doubling, O(n log^2 n)) for indexing emergency reports. */
public class SuffixArray {
    public static int[] build(String s){int n=s.length();Integer[] sa=new Integer[n];int[] rk=new int[n],tmp=new int[n];for(int i=0;i<n;i++){sa[i]=i;rk[i]=s.charAt(i);}
        for(int k=1;;k<<=1){final int K=k;final int[] R=rk;Comparator<Integer> c=(a,b)->R[a]!=R[b]?Integer.compare(R[a],R[b]):Integer.compare(a+K<n?R[a+K]:-1,b+K<n?R[b+K]:-1);Arrays.sort(sa,c);tmp[sa[0]]=0;for(int i=1;i<n;i++)tmp[sa[i]]=tmp[sa[i-1]]+(c.compare(sa[i-1],sa[i])<0?1:0);System.arraycopy(tmp,0,rk,0,n);if(rk[sa[n-1]]==n-1||k>n)break;}
        int[] r=new int[n];for(int i=0;i<n;i++)r[i]=sa[i];return r;}
    /** Binary search all occurrences of pattern using SA. */
    public static List<Integer> find(String s,int[] sa,String p){int lo=0,hi=sa.length;while(lo<hi){int m=(lo+hi)/2;if(cmp(s,sa[m],p)<0)lo=m+1;else hi=m;}List<Integer> r=new ArrayList<>();for(int i=lo;i<sa.length&&cmp(s,sa[i],p)==0;i++)r.add(sa[i]);Collections.sort(r);return r;}
    static int cmp(String s,int i,String p){int l=Math.min(p.length(),s.length()-i);int c=s.substring(i,i+l).compareTo(p.substring(0,l));if(c!=0)return c;return l<p.length()?-1:0;}
    public static void demo(){
        String reports="gas leak|road block|gas leak at mall|";
        int[] sa=build(reports);
        System.out.println("[Suffix Array] Corpus: "+reports);
        System.out.println("  SA (first 10): "+Arrays.toString(Arrays.copyOf(sa,Math.min(10,sa.length))));
        System.out.println("  'gas leak' found at: "+find(reports,sa,"gas leak"));
    }
}
