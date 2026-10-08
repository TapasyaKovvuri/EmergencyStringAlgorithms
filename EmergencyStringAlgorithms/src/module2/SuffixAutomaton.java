package module2;
import java.util.*;
/** Suffix Automaton (compact suffix-tree equivalent) for rapid substring retrieval. */
public class SuffixAutomaton {
    private final List<Map<Character,Integer>> next=new ArrayList<>();private final List<Integer> link=new ArrayList<>(),len=new ArrayList<>(),firstPos=new ArrayList<>();private int last;
    private int add(int l,int fp){next.add(new HashMap<>());link.add(-1);len.add(l);firstPos.add(fp);return next.size()-1;}
    public SuffixAutomaton(String s){last=add(0,-1);for(int i=0;i<s.length();i++)extend(s.charAt(i),i);}
    private void extend(char c,int i){int cur=add(len.get(last)+1,i);int p=last;while(p!=-1&&!next.get(p).containsKey(c)){next.get(p).put(c,cur);p=link.get(p);}
        if(p==-1)link.set(cur,0);else{int q=next.get(p).get(c);if(len.get(p)+1==len.get(q))link.set(cur,q);else{int cl=add(len.get(p)+1,firstPos.get(q));next.get(cl).putAll(next.get(q));link.set(cl,link.get(q));while(p!=-1&&Integer.valueOf(q).equals(next.get(p).get(c))){next.get(p).put(c,cl);p=link.get(p);}link.set(q,cl);link.set(cur,cl);}}last=cur;}
    public boolean contains(String p){return walk(p)!=-1;}
    /** Returns first occurrence index or -1. */
    public int firstOccurrence(String p){int v=walk(p);return v==-1?-1:firstPos.get(v)-p.length()+1;}
    private int walk(String p){int v=0;for(char c:p.toCharArray()){Integer n=next.get(v).get(c);if(n==null)return -1;v=n;}return v;}
    public int states(){return next.size();}
    public static void demo(){
        String log="ambulance dispatched to sector 9; fire truck dispatched to sector 4";
        SuffixAutomaton sam=new SuffixAutomaton(log);
        System.out.println("[Suffix Automaton] Log: "+log+"  (states="+sam.states()+")");
        for(String q:new String[]{"fire truck","sector 4","helicopter"})System.out.println("  '"+q+"' -> contains="+sam.contains(q)+", first at "+sam.firstOccurrence(q));
    }
}
