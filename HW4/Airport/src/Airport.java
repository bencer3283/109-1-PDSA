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
        ArrayList<House> village = new ArrayList<House>();
        for(int i = 0; i < houses.size(); i++){
            village.get(i).x = houses.get(i)[0];
            village.get(i).y = houses.get(i)[1];
        }
        Collections.sort(village); //sort by y
        House origin = village.get(village.size()-1);
        for(int i = 0; i < village.size(); i++){
            double delX = village.get(i).x - origin.x;
            double delY = village.get(i).y - origin.y;
            double tan = Math.toRadians(delX/delY);
            village.get(i).angle = Math.toDegrees(Math.atan(tan));
        }
        Collections.sort(village, new House.polarComparator());

        
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