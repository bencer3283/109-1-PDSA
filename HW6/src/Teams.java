import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.princeton.cs.algs4.Bag;

class Teams {
    private boolean[] colorArray;
    private boolean[] markedArray;
    private boolean isBipartite = true;

    public Teams() {}; 

    public void bfs(ArrayList<Bag<Integer>> G, int v){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(v);
        while(!q.isEmpty()){
            int w = q.remove();
            markedArray[w] = true;
            for(int wv: G.get(w)){
                if(!markedArray[wv]){
                    q.add(wv);
                    colorArray[wv] = ! colorArray[w];
                }
                else if(colorArray[wv] == colorArray[w]) isBipartite = false;
            } 
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
        bfs(G, 0);
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