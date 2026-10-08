package module3;
/** Levenshtein & Damerau-Levenshtein for correcting noisy emergency reports. */
public class Levenshtein {
    public static int levenshtein(String a,String b){int[][] d=new int[a.length()+1][b.length()+1];for(int i=0;i<=a.length();i++)d[i][0]=i;for(int j=0;j<=b.length();j++)d[0][j]=j;
        for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++)d[i][j]=Math.min(Math.min(d[i-1][j]+1,d[i][j-1]+1),d[i-1][j-1]+(a.charAt(i-1)==b.charAt(j-1)?0:1));return d[a.length()][b.length()];}
    /** Optimal string alignment variant (adjacent transpositions). */
    public static int damerau(String a,String b){int[][] d=new int[a.length()+1][b.length()+1];for(int i=0;i<=a.length();i++)d[i][0]=i;for(int j=0;j<=b.length();j++)d[0][j]=j;
        for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++){int c=a.charAt(i-1)==b.charAt(j-1)?0:1;d[i][j]=Math.min(Math.min(d[i-1][j]+1,d[i][j-1]+1),d[i-1][j-1]+c);if(i>1&&j>1&&a.charAt(i-1)==b.charAt(j-2)&&a.charAt(i-2)==b.charAt(j-1))d[i][j]=Math.min(d[i][j],d[i-2][j-2]+1);}return d[a.length()][b.length()];}
    public static String correct(String w,String[] dict){String best=w;int bd=Integer.MAX_VALUE;for(String d:dict){int x=damerau(w,d);if(x<bd){bd=x;best=d;}}return best;}
    public static void demo(){
        String[] dict={"fire","flood","earthquake","ambulance","injured","collapse"};
        String[] noisy={"fier","flod","earthqauke","ambulnce","injrued"};
        System.out.println("[Levenshtein/Damerau] Correcting noisy words:");
        for(String w:noisy)System.out.println("  "+w+" -> "+correct(w,dict)+"  (lev="+levenshtein(w,correct(w,dict))+", dam="+damerau(w,correct(w,dict))+")");
    }
}
