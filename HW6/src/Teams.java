import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Bipartite;

class Teams {
    public Teams() {};

    public boolean teams(int idols, List<int[]> teetee) {
        Graph G = new Graph(idols);
        for(int[] edge: teetee){
            G.addEdge(edge[0], edge[1]);
        }
        Bipartite B = new Bipartite(G);
        return B.isBipartite();
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