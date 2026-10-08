package module3;
import java.util.*;
/** Bitmask DP: choose the cheapest combination of resources covering all needs (set cover). */
public class BitmaskDP {
    public static int[] solve(String[] needs,String[] names,int[][] covers,int[] cost){int n=needs.length,full=(1<<n)-1;int[] dp=new int[1<<n],from=new int[1<<n],pick=new int[1<<n];Arrays.fill(dp,Integer.MAX_VALUE);dp[0]=0;
        int[] mask=new int[names.length];for(int i=0;i<names.length;i++)for(int c:covers[i])mask[i]|=1<<c;
        for(int m=0;m<=full;m++){if(dp[m]==Integer.MAX_VALUE)continue;for(int i=0;i<names.length;i++){int nm=m|mask[i];if(dp[m]+cost[i]<dp[nm]){dp[nm]=dp[m]+cost[i];from[nm]=m;pick[nm]=i;}}}
        if(dp[full]==Integer.MAX_VALUE)return null;List<Integer> ch=new ArrayList<>();for(int m=full;m!=0;m=from[m])ch.add(pick[m]);int[] r=new int[ch.size()+1];r[0]=dp[full];for(int i=0;i<ch.size();i++)r[i+1]=ch.get(i);return r;}
    public static void demo(){
        String[] needs={"Medical","Fire","Rescue","Food","Shelter"};
        String[] units={"Ambulance","FireTruck","NDRF Team","Relief Van","Army Unit"};
        int[][] covers={{0},{1,2},{2,0},{3,4},{2,3,4}};
        int[] cost={4,6,5,3,8};
        int[] r=solve(needs,units,covers,cost);
        System.out.println("[Bitmask DP] Needs: "+Arrays.toString(needs));
        System.out.print("  Minimum cost = "+r[0]+" using:");for(int i=1;i<r.length;i++)System.out.print(" "+units[r[i]]);System.out.println();
    }
}
