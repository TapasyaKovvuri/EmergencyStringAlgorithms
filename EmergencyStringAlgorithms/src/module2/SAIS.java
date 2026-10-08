package module2;
import java.util.*;
/** SA-IS: linear-time suffix array for large-scale incident-document indexing. */
public class SAIS {
    public static int[] build(String str){int n=str.length();int[] s=new int[n+1];for(int i=0;i<n;i++)s[i]=str.charAt(i)+1;s[n]=0;int[] sa=sais(s,65538);return Arrays.copyOfRange(sa,1,sa.length);}
    private static int[] sais(int[] s,int K){int n=s.length;int[] sa=new int[n];boolean[] t=new boolean[n];t[n-1]=true;for(int i=n-2;i>=0;i--)t[i]=s[i]<s[i+1]||(s[i]==s[i+1]&&t[i+1]);
        int[] bkt=new int[K+1];for(int c:s)bkt[c+1]++;for(int i=1;i<=K;i++)bkt[i]+=bkt[i-1];
        int[] lms=new int[n];int m=0;for(int i=1;i<n;i++)if(t[i]&&!t[i-1])lms[m++]=i;lms=Arrays.copyOf(lms,m);
        induce(s,t,sa,bkt,K,lms);
        int[] name=new int[n];Arrays.fill(name,-1);int cnt=0,prev=-1;
        for(int p:sa){if(!isLms(t,p))continue;if(prev<0||!eqLms(s,t,prev,p))cnt++;name[p]=cnt-1;prev=p;}
        int[] s1=new int[m];for(int i=0;i<m;i++)s1[i]=name[lms[i]];
        int[] sa1;if(cnt<m)sa1=sais(s1,cnt);else{sa1=new int[m];for(int i=0;i<m;i++)sa1[s1[i]]=i;}
        int[] sorted=new int[m];for(int i=0;i<m;i++)sorted[i]=lms[sa1[i]];
        induce(s,t,sa,bkt,K,sorted);return sa;}
    private static boolean isLms(boolean[] t,int i){return i>0&&t[i]&&!t[i-1];}
    private static boolean eqLms(int[] s,boolean[] t,int a,int b){int n=s.length;for(int i=0;;i++){boolean ea=isLms(t,a+i),eb=isLms(t,b+i);if(i>0&&ea&&eb)return true;if(ea!=eb||s[a+i]!=s[b+i]||a+i==n-1||b+i==n-1)return false;}}
    private static void induce(int[] s,boolean[] t,int[] sa,int[] bkt,int K,int[] lms){int n=s.length;Arrays.fill(sa,-1);int[] end=new int[K];for(int i=0;i<K;i++)end[i]=bkt[i+1];
        for(int i=lms.length-1;i>=0;i--)sa[--end[s[lms[i]]]]=lms[i];
        int[] head=new int[K];for(int i=0;i<K;i++)head[i]=bkt[i];
        for(int i=0;i<n;i++){int j=sa[i]-1;if(sa[i]>0&&!t[j])sa[head[s[j]]++]=j;}
        for(int i=0;i<K;i++)end[i]=bkt[i+1];
        for(int i=n-1;i>=0;i--){int j=sa[i]-1;if(sa[i]>0&&t[j])sa[--end[s[j]]]=j;}}
    public static void demo(){
        StringBuilder sb=new StringBuilder();String[] docs={"earthquake zone A","flood zone B","earthquake zone C"};for(String d:docs)sb.append(d).append('#');
        String corpus=sb.toString();long t0=System.nanoTime();int[] sa=build(corpus);long t1=System.nanoTime();
        System.out.println("[SA-IS] Indexed "+corpus.length()+" chars in "+(t1-t0)/1000+" us");
        System.out.println("  Matches prefix-doubling SA: "+Arrays.equals(sa,SuffixArray.build(corpus)));
        System.out.println("  'earthquake' at: "+SuffixArray.find(corpus,sa,"earthquake"));
    }
}
