package module3;
/** Matrix-Chain Multiplication: optimal order to combine emergency-data transformation matrices. */
public class MatrixChain {
    private static int[][] split;
    public static long minCost(int[] dims){int n=dims.length-1;long[][] m=new long[n][n];split=new int[n][n];
        for(int L=2;L<=n;L++)for(int i=0;i+L-1<n;i++){int j=i+L-1;m[i][j]=Long.MAX_VALUE;for(int k=i;k<j;k++){long c=m[i][k]+m[k+1][j]+(long)dims[i]*dims[k+1]*dims[j+1];if(c<m[i][j]){m[i][j]=c;split[i][j]=k;}}}return m[0][n-1];}
    public static String order(int i,int j,String[] names){return i==j?names[i]:"("+order(i,split[i][j],names)+" x "+order(split[i][j]+1,j,names)+")";}
    public static void demo(){
        String[] stages={"Sensors","Filter","Geo","Risk","Alert"};
        int[] dims={40,20,30,10,30,5};
        long c=minCost(dims);
        System.out.println("[Matrix-Chain] Pipeline dims: "+java.util.Arrays.toString(dims));
        System.out.println("  Min scalar multiplications = "+c);
        System.out.println("  Optimal order: "+order(0,stages.length-1,stages));
    }
}
