import java.util.Arrays;

public class Warriors {
    public int[] warriors(int[] strength, int[] range) {
        //Given the attributes of each warriors and output the minimal and maximum 
        //index of warrior can be attacked by each warrior.
        return new int[]{};
    }

    public static void main(String[] args) {
        Warriors sol = new Warriors();
        System.out.println(Arrays.toString(
            sol.warriors(new int[] {11, 13, 11, 7, 15},
                        new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
    }
}
