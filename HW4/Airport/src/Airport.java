import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import edu.princeton.cs.algs4.Point2D;

class Airport {
    private int check_CCW(Point2D house_i, List<Point2D> hull){
        int last_hull_index = hull.size()-1;
        Point2D last_hull = hull.get(last_hull_index);
        Point2D last_last_hull = hull.get(last_hull_index-1);
        int ccw = Point2D.ccw(last_last_hull, last_hull, house_i);
        if(ccw == -1){
            hull.remove(last_hull_index);
            return check_CCW(house_i, hull);
        }
        else if(ccw == 0){
            hull.remove(last_hull_index);
            return 0;
        }
        else return 0;
    }
    public double airport(List<int[]> houses) {
        // Output smallest average distance with optimal selection of airport location.
        if(houses.size() < 3) return 0;
        double avg_dis = 0;
        ArrayList<Point2D> village = new ArrayList<Point2D>();
        ArrayList<Point2D> hull = new ArrayList<Point2D>();
        for(int i = 0; i < houses.size(); i++){
            Point2D housetoadd = new Point2D(houses.get(i)[0], houses.get(i)[1]);
            village.add(housetoadd);
        }
        // find convex hull
        Point2D origin = village.get(0);
        for(int i = 0; i < village.size(); i++){
            if(village.get(i).y() < origin.y()) origin = village.get(i);
        }
        for(int i = 0; i < village.size(); i++){
            if(village.get(i).y() == origin.y() && village.get(i).x() == origin.x()){
                village.remove(i);
                break;
            }
        }
        Collections.sort(village, origin.distanceToOrder());
        Collections.sort(village, origin.polarOrder());
        hull.add(origin);
        hull.add(village.get(0));
        for(int i = 1; i < village.size(); i++){
            check_CCW(village.get(i), hull);
            hull.add(village.get(i));
        }
        if(hull.size() < 3) return 0;
        if(Point2D.ccw(hull.get(hull.size()-1), hull.get(0), hull.get(1)) == 0) hull.remove(0);
        if(hull.size()>2 && Point2D.ccw(hull.get(hull.size()-2), hull.get(hull.size()-1), hull.get(0)) == 0) hull.remove(hull.size()-1);
        
        // calculate inner equivalent point
        double equiX = origin.x();
        double equiY = origin.y();
        for (int i = 0; i < village.size(); i++){
            double x = village.get(i).x();
            double y = village.get(i).y();
            equiX += x;
            equiY += y;
        }
        double villagesize = village.size() + 1;
        equiX /= villagesize;
        equiY /= villagesize;

        // for each possible runway calculate the average distance
        avg_dis = 0;
        double lx = hull.get(hull.size()-1).x() - hull.get(0).x();
        double ly = hull.get(hull.size()-1).y() - hull.get(0).y();
        double k = lx*hull.get(0).y() - ly*hull.get(0).x();
        avg_dis = Math.abs((equiX*ly-equiY*lx+k)/Math.sqrt(lx*lx+ly*ly));
        double dis = 0;
        for(int i = 0; i < hull.size()-1; i++){
            lx = hull.get(i).x() - hull.get(i+1).x();
            ly = hull.get(i).y() - hull.get(i+1).y();
            k = lx*hull.get(i).y() - ly*hull.get(i).x();
            dis = Math.abs((equiX*ly-equiY*lx+k)/Math.sqrt(lx*lx+ly*ly));
            if(Double.compare(dis, avg_dis) < 0) avg_dis = dis;
        }
        return avg_dis;
    }   

    public static void main(String[] args) {
        // More example are in Python template
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{4,12});
            add(new int[]{2,10});
            add(new int[]{1,8});
            add(new int[]{4,1});
            add(new int[]{3,2});
            add(new int[]{2,3});
            add(new int[]{1,5});
            add(new int[]{1,7});
            add(new int[]{1,6});
            add(new int[]{5,0});
            add(new int[]{6,0});
            add(new int[]{8,0});
            add(new int[]{9,0});
            add(new int[]{7,0});
            add(new int[]{6,13});
            add(new int[]{9,13});
            add(new int[]{11,1});
            add(new int[]{13,2});
            add(new int[]{14,5});
            add(new int[]{14,8});
            add(new int[]{13,11});
            add(new int[]{11,12});
            add(new int[]{9,13});
        }}));
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{1,9});
            add(new int[]{2,8});
            add(new int[]{3,7});
            add(new int[]{4,6});
            add(new int[]{5,5});
            add(new int[]{6,4});
            add(new int[]{7,3});
            add(new int[]{8,2});
            add(new int[]{9,1});
            add(new int[]{10,0});
        }}));
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{1,1});
            add(new int[]{2,2});
        }}));
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