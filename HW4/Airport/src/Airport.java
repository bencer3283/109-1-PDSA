import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Math;
import edu.princeton.cs.algs4.Point2D;

class House implements Comparable<House>{
    public int x = 0;
    public int y = 0;
    public double angle = 0;

    @Override
    public int compareTo(House that) {
        if(this.y != that.y)return this.y-that.y;
        else return this.x-that.x; 
    }

    public static class polarComparator implements Comparator<House>{
        @Override
        public int compare(House one, House two){
            return Double.compare(one.angle, two.angle);
        }
    }
}


class Airport {
    private int check_CCW(Point2D house_i, List<House> hull, List<House> inner, List<House> between){
        int last_hull_index = hull.size()-1;
        Point2D last_hull = new Point2D(hull.get(last_hull_index).x, hull.get(last_hull_index).y);
        Point2D last_last_hull = new Point2D(hull.get(last_hull_index-1).x, hull.get(last_hull_index-1).y);
        int ccw = Point2D.ccw(last_last_hull, last_hull, house_i);
        if(ccw == -1){
            inner.add(hull.get(last_hull_index));
            hull.remove(last_hull_index);
            return check_CCW(house_i, hull, inner, between);
        }
        // else if(ccw == 0){
        //     between.add(hull.get(last_hull_index));
        //     hull.remove(last_hull_index);
        //     return 0;
        // }
        else return 0;
    }
    public double airport(List<int[]> houses) {
        // Output smallest average distance with optimal selection of airport location.
        double avg_dis = 0;
        ArrayList<House> village = new ArrayList<House>();
        ArrayList<House> hull = new ArrayList<House>();
        ArrayList<House> inner = new ArrayList<House>();
        ArrayList<House> between = new ArrayList<House>();
        for(int i = 0; i < houses.size(); i++){
            House housetoadd = new House();
            housetoadd.x = houses.get(i)[0];
            housetoadd.y = houses.get(i)[1];
            village.add(housetoadd);
        }
        // find convex hull
        Collections.sort(village); //sort by y
        House origin = village.get(0);
        village.remove(0);
        for(int i = 0; i < village.size(); i++){
            double delX = village.get(i).x - origin.x;
            double delY = village.get(i).y - origin.y;
            if(delX == 0) village.get(i).angle = 90;
            else if(delY == 0){
                if(delX > 0) village.get(i).angle = 0;
                if(delX < 0) village.get(i).angle = 180;
            }
            else{
                double tan = delY/delX;
                village.get(i).angle = Math.toDegrees(Math.atan(tan));
                if(village.get(i).angle < 0) village.get(i).angle += 180;
            }
        }
        Collections.sort(village, new House.polarComparator());
        hull.add(origin);
        hull.add(village.get(0));
        for(int i = 1; i < village.size(); i++){
            Point2D house_i = new Point2D(village.get(i).x, village.get(i).y);
            check_CCW(house_i, hull, inner, between);
            hull.add(village.get(i));
        }
        for(int i = 1; i < hull.size()-1; i++){
            Point2D me = new Point2D(hull.get(i).x, hull.get(i).y);
            Point2D prior = new Point2D(hull.get(i-1).x, hull.get(i-1).y);
            Point2D after= new Point2D(hull.get(i+1).x, hull.get(i+1).y);
            if(Point2D.ccw(prior, me, after) == 0){
                between.add(hull.get(i));
                hull.remove(i);
                i--;
            }
        }
        // calculate inner equivalent point
        double equiX = 0;
        double equiY = 0;
        for (int i = 0; i < inner.size(); i++){
            equiX += inner.get(i).x / inner.size();
            equiY += inner.get(i).y / inner.size();
        }
        // for each possible runway calculate the average distance
        double lx = hull.get(hull.size()-1).x - hull.get(0).x;
        double ly = hull.get(hull.size()-1).y - hull.get(0).y;
        double k = lx*hull.get(0).y - ly*hull.get(0).x;
        for(int i = 0; i < hull.size(); i++){
            double px = hull.get(i).x;
            double py = hull.get(i).y;
            avg_dis += Math.abs((px*ly-py*lx+k)/Math.sqrt(lx*lx+ly*ly));
        }
        for(int i = 0; i < between.size(); i++){
            double px = between.get(i).x;
            double py = between.get(i).y;
            avg_dis += Math.abs((px*ly-py*lx+k)/Math.sqrt(lx*lx+ly*ly));
        }
        avg_dis += Math.abs((equiX*ly-equiY*lx+k)/Math.sqrt(lx*lx+ly*ly))*inner.size();
        avg_dis /= (village.size()+1);
        for(int i = 1; i < hull.size()-1; i++){
            double dis = 0;
            lx = hull.get(i).x - hull.get(i+1).x;
            ly = hull.get(i).y - hull.get(i+1).y;
            k = lx*hull.get(i).y - ly*hull.get(i).x;
            for(int j = 0; j < hull.size(); j++){
                double px = hull.get(j).x;
                double py = hull.get(j).y;
                dis += Math.abs((px*ly-py*lx+k)/Math.sqrt(lx*lx+ly*ly));
            }
            for(int j = 0; j < between.size(); j++){
                double px = between.get(j).x;
                double py = between.get(j).y;
                dis += Math.abs((px*ly-py*lx+k)/Math.sqrt(lx*lx+ly*ly));
            }
            dis += Math.abs((equiX*ly-equiY*lx+k)/Math.sqrt(lx*lx+ly*ly))*inner.size();
            dis /= (village.size()+1);
            if(Double.compare(dis, avg_dis) < 0) avg_dis = dis;
        }
        return avg_dis;
    }   

    public static void main(String[] args) {
        // More example are in Python template
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    add(new int[]{i,j});
                }
            }
        }}));
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{1,1});
            add(new int[]{2,2});
            add(new int[]{0,2});
            add(new int[]{2,0});
            add(new int[]{2,4});
            add(new int[]{3,3});
            add(new int[]{4,2});
            add(new int[]{4,1});
            add(new int[]{4,0});
        }}));
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{0,0});
            add(new int[]{2,2});
            add(new int[]{0,2});
            add(new int[]{2,0});
            add(new int[]{1,1});
        }}));
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{0,0});
            add(new int[]{1,0});
            add(new int[]{0,1});
        }}));
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{3,1});
            add(new int[]{8,1});
            add(new int[]{4,2});
            add(new int[]{7,2});
            add(new int[]{10,2});
            add(new int[]{12,2});
            add(new int[]{2,3});
            add(new int[]{3,4});
            add(new int[]{5,4});
            add(new int[]{6,4});
            add(new int[]{9,4});
            add(new int[]{10,4});
            add(new int[]{2,5});
            add(new int[]{4,6});
            add(new int[]{6,6});
            add(new int[]{8,6});
            add(new int[]{10,6});
            add(new int[]{1,7});
            add(new int[]{6,7});
            add(new int[]{3,8});
            add(new int[]{8,8});
            add(new int[]{9,8});
            add(new int[]{11,8});
            add(new int[]{12,8});
            add(new int[]{2,9});
            add(new int[]{5,9});
            add(new int[]{4,11});
            add(new int[]{7,11});
            add(new int[]{6,1});
            add(new int[]{3,10});
        }}));
    }   
}