package main;
/** Runs demos of all three modules. Right-click > Run As > Java Application. */
public class Main {
    public static void main(String[] args){
        System.out.println("===== MODULE 1 : STRING ALGORITHMS (CO1) =====");
        module1.KMP.demo(); module1.ZFunction.demo(); module1.RabinKarp.demo(); module1.AhoCorasick.demo();
        System.out.println("\n===== MODULE 2 : SUFFIX STRUCTURES (CO2) =====");
        module2.SuffixArray.demo(); module2.SAIS.demo(); module2.LCPKasai.demo(); module2.SuffixAutomaton.demo();
        System.out.println("\n===== MODULE 3 : ADVANCED DYNAMIC PROGRAMMING (CO3) =====");
        module3.Levenshtein.demo(); module3.WeightedEditDistance.demo(); module3.BitmaskDP.demo(); module3.MatrixChain.demo();
    }
}
