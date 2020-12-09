import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.lang.Math;
import edu.princeton.cs.algs4.Point2D;

class PairOfCluster{
    Point2D a;
    Point2D b;
    int a_index;
    int b_index;
}

class Cluster {
    public ArrayList<Point2D> cluster(List<int[]> points, int cluster_num) {
        ArrayList<Point2D> p = new ArrayList<Point2D>();
        for(int[] i: points) {
            p.add(new Point2D(i[0], i[1]));
        }

        HashMap<Point2D, ArrayList<Point2D>> clusterMap = new HashMap<Point2D, ArrayList<Point2D>>();
        for(Point2D i: p){
            clusterMap.put(i, new ArrayList<Point2D>(){{add(i);}});
        }
        
        int n = p.size();
        while(n > cluster_num){
            //find nearest pair of cluster 
            Collections.sort(p, new Point2D(0, 0).distanceToOrder());
            PairOfCluster nearestPair = new PairOfCluster();
            nearestPair.a = p.get(p.size()-1);
            nearestPair.b = p.get(0);
            double nearestDis = p.get(p.size()-1).distanceTo(p.get(0));
            for(int i = 0; i < p.size()-1; i++){
                double dis = p.get(i).distanceTo(p.get(i+1));
                if(dis < nearestDis){
                    nearestDis = dis;
                    nearestPair.a = p.get(i);
                    nearestPair.b = p.get(i+1);
                    nearestPair.a_index = i;
                    nearestPair.b_index = i+1;
                }
            }

            //delete old nearest pair
            p.remove(nearestPair.b_index);
            p.remove(nearestPair.a_index);

            //calculate centroid of nearest pair
            double x_sum = 0;
            double y_sum = 0;
            for(Point2D i: clusterMap.get(nearestPair.a)){
                x_sum += i.x();
                y_sum += i.y();
            }
            for(Point2D i: clusterMap.get(nearestPair.b)){
                x_sum += i.x();
                y_sum += i.y();
            }
            x_sum /= (clusterMap.get(nearestPair.a).size()+clusterMap.get(nearestPair.b).size());
            y_sum /= (clusterMap.get(nearestPair.a).size()+clusterMap.get(nearestPair.b).size());

            //add new cluster
            p.add(new Point2D(x_sum, y_sum));
            ArrayList<Point2D> newcluster = new ArrayList<Point2D>();
            for(Point2D i: clusterMap.get(nearestPair.a)){
                newcluster.add(i);
            }
            for(Point2D i: clusterMap.get(nearestPair.b)){
                newcluster.add(i);
            }
            clusterMap.put(new Point2D(x_sum, y_sum), newcluster);
            n--;
        }
        Collections.sort(p, Point2D.Y_ORDER);
        Collections.sort(p, Point2D.X_ORDER);

        return p;
    }

    public static void main(String[] args) {
        System.out.println(new Cluster().cluster(new ArrayList<int[]>(){{
            add(new int[]{0,1});
            add(new int[]{0,2});
            add(new int[]{3,1});
            add(new int[]{3,2});
        }}, 2));
        System.out.println(new Cluster().cluster(new ArrayList<int[]>(){{
            add(new int[]{0,0});
            add(new int[]{1,0});
            add(new int[]{3,0});
            add(new int[]{0,1});
        }}, 2));
        System.out.println(new Cluster().cluster(new ArrayList<int[]>(){{
            add(new int[]{0,3});
            add(new int[]{3,3});
            add(new int[]{4,7});
            add(new int[]{9,0});
            add(new int[]{9,4});
        }}, 3));
    }
}