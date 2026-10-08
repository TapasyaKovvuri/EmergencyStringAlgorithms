package module3;
/** Weighted Edit Distance: different costs for different reporting errors. */
public class WeightedEditDistance {
    public static double insCost=1.0, delCost=1.0;
    /** Substituting digits (e.g. in incident codes / phone numbers) is costlier; keyboard-adjacent typos cheaper. */
    public static double subCost(char a,char b){if(a==b)return 0;if(Character.isDigit(a)||Character.isDigit(b))return 3.0;if(adjacent(a,b))return 0.5;return 1.0;}
    static final String[] ROWS={"qwertyuiop","asdfghjkl","zxcvbnm"};
    static boolean adjacent(char a,char b){a=Character.toLowerCase(a);b=Character.toLowerCase(b);for(int r=0;r<3;r++){int i=ROWS[r].indexOf(a);if(i<0)continue;for(int r2=Math.max(0,r-1);r2<=Math.min(2,r+1);r2++){int j=ROWS[r2].indexOf(b);if(j>=0&&Math.abs(i-j)<=1)return true;}}return false;}
    public static double distance(String a,String b){double[][] d=new double[a.length()+1][b.length()+1];for(int i=1;i<=a.length();i++)d[i][0]=i*delCost;for(int j=1;j<=b.length();j++)d[0][j]=j*insCost;
        for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++)d[i][j]=Math.min(Math.min(d[i-1][j]+delCost,d[i][j-1]+insCost),d[i-1][j-1]+subCost(a.charAt(i-1),b.charAt(j-1)));return d[a.length()][b.length()];}
    public static void demo(){
        System.out.println("[Weighted Edit Distance]");
        String[][] p={{"fire","fore"},{"fire","fize"},{"INC-2041","INC-2047"},{"flood","flold"}};
        for(String[] x:p)System.out.printf("  %s vs %s -> weighted=%.1f, plain=%d%n",x[0],x[1],distance(x[0],x[1]),Levenshtein.levenshtein(x[0],x[1]));
    }
}
