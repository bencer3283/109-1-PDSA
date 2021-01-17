import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.DijkstraSP;

class Mountains {
    public Mountains() {};

    public int mountains(List<int[]> mountain_height) {
        int no_row = mountain_height.size();
        int no_column = mountain_height.get(0).length;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(no_row*no_column);
        for(int row = 0; row < no_row; row++){
            for(int col = 0; col < no_column; col++){
                if(row != 0){
                    double weight = 0;
                    double i_weight = mountain_height.get(row)[col];
                    double j_weight = mountain_height.get(row-1)[col];
                    if(i_weight >= j_weight) weight = i_weight - j_weight;
                    else weight =  2 * (j_weight-i_weight);
                    DirectedEdge e = new DirectedEdge((row)*no_column+col, (row-1)*no_column+col, weight);
                    G.addEdge(e);
                }
                if(row != no_row-1){
                    double weight = 0;
                    double i_weight = mountain_height.get(row)[col];
                    double j_weight = mountain_height.get(row+1)[col];
                    if(i_weight >= j_weight) weight = i_weight - j_weight;
                    else weight =  2 * (j_weight-i_weight);
                    DirectedEdge e = new DirectedEdge((row)*no_column+col, (row+1)*no_column+col, weight);
                    G.addEdge(e);
                }
                if(col != 0){
                    double weight = 0;
                    double i_weight = mountain_height.get(row)[col];
                    double j_weight = mountain_height.get(row)[col-1];
                    if(i_weight >= j_weight) weight = i_weight - j_weight;
                    else weight =  2 * (j_weight-i_weight);
                    DirectedEdge e = new DirectedEdge((row)*no_column+col, (row)*no_column+col-1, weight);
                    G.addEdge(e);
                }
                if(col != no_column-1){
                    double weight = 0;
                    double i_weight = mountain_height.get(row)[col];
                    double j_weight = mountain_height.get(row)[col+1];
                    if(i_weight >= j_weight) weight = i_weight - j_weight;
                    else weight =  2 * (j_weight-i_weight);
                    DirectedEdge e = new DirectedEdge((row)*no_column+col, (row)*no_column+col+1, weight);
                    G.addEdge(e);
                }
            }
        }
        DijkstraSP SPT = new DijkstraSP(G, 0);
        return (int)SPT.distTo(no_row*no_column-1);
    }

    public static void main(String[] args) {
        Mountains m = new Mountains();
        System.out.println(m.mountains(new ArrayList<int[]>(){{
            add(new int[]{ 0,  1,  2,  3,  4});
            add(new int[]{24, 23, 22, 21,  5});
            add(new int[]{12, 13, 14, 15, 16});
            add(new int[]{11, 17, 18, 19, 20});
            add(new int[]{10,  9,  8,  7,  6});
        }}));
        // 42
        System.out.println(m.mountains(new ArrayList<int[]>(){{
            add(new int[]{3,4,5});
            add(new int[]{9,3,5});
            add(new int[]{7,4,3});
        }}));
        // 6
    }
}