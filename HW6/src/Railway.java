import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;
import edu.princeton.cs.algs4.Edge;
import java.util.ArrayList;
import java.util.List;

public class Railway {
    public Railway(){}

    public int railway(int No_landmark, List<int[]> distance ){
        EdgeWeightedGraph G = new EdgeWeightedGraph(No_landmark);
        for(int[] edge: distance){
            Edge e = new Edge(edge[0], edge[1], edge[2]);
            G.addEdge(e);
        }
        PrimMST mst = new PrimMST(G);
        return (int)mst.weight();
    }

    public static void main(String[] args) {
        Railway team = new Railway();
        System.out.println(team.railway(4, new ArrayList<int[]>(){{
            add(new int[]{0,1,2});
            add(new int[]{0,2,4});
            add(new int[]{1,3,5});
            add(new int[]{2,1,1});
        }}));
        System.out.println(team.railway(4, new ArrayList<int[]>(){{
            add(new int[]{0,1,0});
            add(new int[]{0,2,4});
            add(new int[]{0,3,4});
            add(new int[]{1,2,1});
            add(new int[]{1,3,4});
            add(new int[]{2,3,2});
        }}));
    }
}
