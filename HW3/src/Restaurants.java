import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;


class Restaurant implements Comparable<Restaurant> {
    int ID;
    int rating;
    int pricing;
    int dist;
    float value;
    
    Restaurant(int id, int rate, int price, int distance) {
        ID = id;
        rating = rate;
        pricing = price;
        dist = distance;
        value = dist*pricing/rating;        
    }

    public int getID() {
        return ID;
    }

    @Override
    public int compareTo(Restaurant b) {
        int comparison = 0;
        if(this.value - b.value < 0) comparison = -1;
        else if(this.value - b.value > 0) comparison = 1;
        else comparison = 0;
        return comparison;
    }

    public static class Comparator2 implements Comparator<Restaurant>{
        @Override
        public int compare(Restaurant a, Restaurant b){
            if(a.dist != b.dist) return a.dist - b.dist;
            else return b.ID - a.ID;
        }
    }

    public static class Comparator1 implements Comparator<Restaurant> {
        @Override
        public int compare(Restaurant a, Restaurant b) {
            if(a.rating != b.rating) return a.rating - b.rating;
            else{
                if(a.dist != b.dist) return a.dist - b.dist;
                else return -(a.ID - b.ID);
            }
        }
    }
}


class Restaurants {
    ArrayList<Restaurant> rate5 = new ArrayList<Restaurant>();
    ArrayList<Restaurant> rate4 = new ArrayList<Restaurant>();
    ArrayList<Restaurant> rate3 = new ArrayList<Restaurant>();
    ArrayList<Restaurant> rate2 = new ArrayList<Restaurant>();
    ArrayList<Restaurant> rate1 = new ArrayList<Restaurant>();
    List<Restaurant> allrestaurant = new ArrayList<Restaurant>();
    
    public Restaurants(List<Restaurant> restaurants) {
        allrestaurant = restaurants;
        for (int i = 0; i < restaurants.size(); i++){
            if (restaurants.get(i).rating == 1){
                rate5.add(restaurants.get(i));
            }
            if (restaurants.get(i).rating == 2){
                rate4.add(restaurants.get(i));
            }
            if (restaurants.get(i).rating == 3){
                rate3.add(restaurants.get(i));
            }
            if (restaurants.get(i).rating == 4){
                rate2.add(restaurants.get(i));
            }
            if (restaurants.get(i).rating == 5){
                rate1.add(restaurants.get(i));
            }

        }
    }

    public int[] filter(int min_price, int max_price, int min_rate) {
        List<Restaurant> result = new ArrayList<Restaurant>();
        if(min_rate == 1){
            result.addAll(rate1);
        }
        else if(min_rate == 2){
            result.addAll(rate1);
            result.addAll(rate2);
        }
        else if(min_rate == 3){
            result.addAll(rate1);
            result.addAll(rate2);
            result.addAll(rate3);
        }
        else if(min_rate == 4){
            result.addAll(rate1);
            result.addAll(rate2);
            result.addAll(rate3);
            result.addAll(rate4);
        }
        else if(min_rate == 5){
            result = allrestaurant;
        }

        Collections.sort(result, new Restaurant.Comparator2());
        int[] output = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            output[i] = result.get(i).ID;
        } 
        return output;
    }

    public static void main(String[] args) {
        // test
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(new Restaurant(20, 1, 20, 12));
        restaurants.add(new Restaurant(15, 3, 19, 11));
        restaurants.add(new Restaurant(19, 4, 19, 12));
        restaurants.add(new Restaurant(18, 5, 20, 11));
        Restaurants r = new Restaurants(restaurants);
        System.out.println(Arrays.toString(r.filter(0, 25, 3)));
        System.out.println(Arrays.toString(r.filter(0, 25, 4)));
        System.out.println(Arrays.toString(r.filter(0, 20, 1)));
        System.out.println(Arrays.toString(r.filter(0, 10, 1)));
        System.out.println(Arrays.toString(r.filter(0, 19, 1)));
        System.out.println(Arrays.toString(r.filter(19, 19, 3)));

        // case 6
        restaurants = new ArrayList<Restaurant>();
        restaurants.add(new Restaurant(3, 2, 3, 8));
        restaurants.add(new Restaurant(0, 2, 4, 6));
        restaurants.add(new Restaurant(2, 4, 5, 12));
        restaurants.add(new Restaurant(1, 5, 6, 11));

        Collections.sort(restaurants);
        for (Restaurant a: restaurants) {
            System.out.print(a.getID());
            System.out.print(" ");
        }
        System.out.println("");

        Collections.sort(restaurants, new Restaurant.Comparator1());
        for (Restaurant a: restaurants) {
            System.out.print(a.getID());
            System.out.print(" ");
        }
        System.out.println("");
    }
}