import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;


class Restaurant implements Comparable<Restaurant> {
    Restaurant(int id, int rate, int price, int distance) {
    }

    public int getID() {
        return 0;
    }

    @Override
    public int compareTo(Restaurant b) {
        return 0;
    }

    public static class Comparator1 implements Comparator<Restaurant> {
        public int compare(Restaurant a, Restaurant b) {
            return 0;
        }
    }
}


class Restaurants {
    public Restaurants(List<Restaurant> restaurants) {
    }

    public int[] filter(int min_price, int max_price, int min_rate) {
        return new int[];
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