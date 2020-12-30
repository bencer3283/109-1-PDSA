import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Bag;

class Teams {
    private boolean[] colorArray;
    private boolean[] markedArray;
    private boolean isBipartite = true;

    public Teams() {}; 

    public void dfs(ArrayList<Bag<Integer>> G, int v){
        markedArray[v] = true;
        for(int vv: G.get(v)){
            if(!markedArray[vv]){
                colorArray[vv] = !colorArray[v];
                dfs(G, vv);
            }
            else if(colorArray[v] == colorArray[vv]) isBipartite = false;
        }
    }

    public boolean teams(int idols, List<int[]> teetee) {
        ArrayList<Bag<Integer>> G = new ArrayList<Bag<Integer>>();
        for(int i = 0; i < idols; i++){
            Bag B = new Bag<Integer>();
            G.add(B);
        }
        for(int[] edge: teetee){
            int a = edge[0];
            int b = edge[1];
            G.get(a).add(b);
            G.get(b).add(a);
        }
        colorArray = new boolean[idols];
        markedArray = new boolean[idols];
        for(int i = 0; i < idols; i++){
            if(!markedArray[i]) dfs(G, i);
        }
        return isBipartite;
    }   

    public static void main(String[] args) {
        Teams team = new Teams();
        // example1: True
        System.out.println(team.teams(4, new ArrayList<int[]>(){{
            add(new int[]{0,1});
            add(new int[]{0,3});
            add(new int[]{2,1});
            add(new int[]{3,2});
        }}));
        // example2: False
        System.out.println(team.teams(4, new ArrayList<int[]>(){{
            add(new int[]{0,1});
            add(new int[]{0,3});
            add(new int[]{2,1});
            add(new int[]{3,2});
            add(new int[]{0,2});
        }}));
    }   
}