import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.ClosestPair;

class Cluster {
    public List<double[]> cluster(List<int[]> points, int cluster_num) {
        Point2D[] p;
        p = new Point2D[points.size()];
        for(int i = 0; i < points.size(); i++) {
            p[i] = new Point2D(points.get(i)[0], points.get(i)[1]);
        }

        HashMap<Point2D, ArrayList<Point2D>> clusterMap = new HashMap<Point2D, ArrayList<Point2D>>();
        for(Point2D i: p){
            clusterMap.put(i, new ArrayList<Point2D>(){{add(i);}});
        }
        
        int n = p.length;
        while(n > cluster_num){
            //find nearest pair of cluster 
            ClosestPair nearestPair = new ClosestPair(p);

            //delete old nearest pair
            int a_index = 0;
            int b_index = 0;
            for(int i = 0; i < p.length; i++){
                if(p[i].equals(nearestPair.either())){
                    a_index = i;
                    break;
                }
            }
            Point2D[] copy = new Point2D[p.length - 1];

            for (int i = 0, j = 0; i < p.length; i++) {
                if (i != a_index) {
                    copy[j++] = p[i];
                }
            }
            p = copy;
            for(int i = 0; i < p.length; i++){
                if(p[i].equals(nearestPair.other())){
                    b_index = i;
                    break;
                }
            }
            Point2D[] copy2 = new Point2D[p.length - 1];
            for (int i = 0, j = 0; i < p.length; i++) {
                if (i != b_index) {
                    copy2[j++] = p[i];
                }
            }
            p = copy2;

            //calculate centroid of nearest pair
            double x_sum = 0;
            double y_sum = 0;
            for(Point2D i: clusterMap.get(nearestPair.either())){
                x_sum += i.x();
                y_sum += i.y();
            }
            for(Point2D i: clusterMap.get(nearestPair.other())){
                x_sum += i.x();
                y_sum += i.y();
            }
            x_sum /= (clusterMap.get(nearestPair.either()).size()+clusterMap.get(nearestPair.other()).size());
            y_sum /= (clusterMap.get(nearestPair.either()).size()+clusterMap.get(nearestPair.other()).size());

            //add new cluster
            Point2D[] copy3 = new Point2D[p.length+1];
            for(int i = 0; i < p.length; i++){
                copy3[i] = p[i];
            }
            copy3[copy3.length - 1] = new Point2D(x_sum, y_sum);
            p = copy3;
            ArrayList<Point2D> newcluster = new ArrayList<Point2D>();
            for(Point2D i: clusterMap.get(nearestPair.either())){
                newcluster.add(i);
                clusterMap.remove(i);
            }
            for(Point2D i: clusterMap.get(nearestPair.other())){
                newcluster.add(i);
                clusterMap.remove(i);
            }
            clusterMap.put(new Point2D(x_sum, y_sum), newcluster);
            n--;
        }
        Arrays.sort(p, Point2D.Y_ORDER);
        Arrays.sort(p, Point2D.X_ORDER);

        ArrayList<double[]> ans = new ArrayList<double[]>();
        for(Point2D i : p){
            double[] subans = new double[2];
            subans[0] = i.x();
            subans[1] = i.y();
            ans.add(subans);
        }

        return ans;
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