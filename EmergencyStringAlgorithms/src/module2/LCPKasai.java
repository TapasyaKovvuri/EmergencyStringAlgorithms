package module2;
/** Kasai LCP: identify repeated incident descriptions. */
public class LCPKasai {
    public static int[] lcp(String s,int[] sa){int n=s.length();int[] rk=new int[n],l=new int[n];for(int i=0;i<n;i++)rk[sa[i]]=i;for(int i=0,h=0;i<n;i++){if(rk[i]>0){int j=sa[rk[i]-1];while(i+h<n&&j+h<n&&s.charAt(i+h)==s.charAt(j+h))h++;l[rk[i]]=h;if(h>0)h--;}else h=0;}return l;}
    public static String longestRepeated(String s){int[] sa=SAIS.build(s);int[] l=lcp(s,sa);int best=0,pos=0;for(int i=1;i<l.length;i++)if(l[i]>best){best=l[i];pos=sa[i];}return s.substring(pos,pos+best);}
    public static long distinctSubstrings(String s){int[] sa=SAIS.build(s);int[] l=lcp(s,sa);long n=s.length(),t=n*(n+1)/2;for(int x:l)t-=x;return t;}
    public static void demo(){
        String desc="building collapsed people trapped; road flooded; building collapsed people trapped";
        System.out.println("[LCP/Kasai] Descriptions: "+desc);
        System.out.println("  Longest repeated description: '"+longestRepeated(desc).trim()+"'");
        System.out.println("  Distinct substrings in 'alertalert': "+distinctSubstrings("alertalert"));
    }
}
