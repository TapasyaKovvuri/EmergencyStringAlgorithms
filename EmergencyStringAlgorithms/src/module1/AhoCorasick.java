package module1;
import java.util.*;
/** Aho-Corasick: detect multiple emergency keywords simultaneously. */
public class AhoCorasick {
    private final List<Map<Character,Integer>> go=new ArrayList<>();private final List<Integer> fail=new ArrayList<>();private final List<List<Integer>> out=new ArrayList<>();private final String[] words;
    private int node(){go.add(new HashMap<>());fail.add(0);out.add(new ArrayList<>());return go.size()-1;}
    public AhoCorasick(String... w){words=w;node();for(int i=0;i<w.length;i++){int c=0;for(char ch:w[i].toCharArray()){Integer nx=go.get(c).get(ch);if(nx==null){nx=node();go.get(c).put(ch,nx);}c=nx;}out.get(c).add(i);}
        Deque<Integer> q=new ArrayDeque<>();for(int v:go.get(0).values())q.add(v);
        while(!q.isEmpty()){int u=q.poll();for(Map.Entry<Character,Integer> e:go.get(u).entrySet()){int v=e.getValue();int f=fail.get(u);while(f>0&&!go.get(f).containsKey(e.getKey()))f=fail.get(f);Integer t=go.get(f).get(e.getKey());fail.set(v,(t!=null&&t!=v)?t:0);out.get(v).addAll(out.get(fail.get(v)));q.add(v);}}}
    public Map<String,List<Integer>> search(String text){Map<String,List<Integer>> r=new LinkedHashMap<>();for(String w:words)r.put(w,new ArrayList<>());int c=0;for(int i=0;i<text.length();i++){char ch=text.charAt(i);while(c>0&&!go.get(c).containsKey(ch))c=fail.get(c);c=go.get(c).getOrDefault(ch,0);for(int id:out.get(c))r.get(words[id]).add(i-words[id].length()+1);}return r;}
    public static void demo(){
        String text="flood near bridge, fire in hospital, injured people, flood rising";
        System.out.println("[Aho-Corasick] Text: "+text);
        new AhoCorasick("flood","fire","injured","hospital","collapse").search(text).forEach((k,v)->System.out.println("  "+k+" -> "+v));
    }
}
