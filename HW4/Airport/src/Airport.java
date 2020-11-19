import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Math;
import edu.princeton.cs.algs4.Point2D;

class House implements Comparable<House>{
    public int x = 0;
    public int y = 0;
    public double angle = 0;

    @Override
    public int compareTo(House that) {
        return this.y-that.y;
    }

    public static class polarComparator implements Comparator<House>{
        @Override
        public int compare(House one, House two){
            return Double.compare(one.angle, two.angle);
        }
    }

}


class Airport {
    public double airport(List<int[]> houses) {
        // Output smallest average distance with optimal selection of airport location.
        double avg_dis = 0;

        
        return avg_dis; 
    }   

    public static void main(String[] args) {
        // More example are in Python template
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{0,0});
            add(new int[]{1,0});
            add(new int[]{0,1});
        }}));
    }   
}