import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class Warriors {
    
    public int[] warriors(int[] strength, int[] range) {
        //Given the attributes of each warriors and output the minimal and maximum 
        //index of warrior can be attacked by each warrior.
        int[] ans = new int[strength.length*2];
        ans[0] = 0;
        ans[strength.length*2 - 1] = strength.length - 1;
        Stack<Integer> fromLeft = new Stack<Integer>();
        Stack<Integer> fromRight = new Stack<Integer>();
        for(int i = 0; i < strength.length; i++){
            while(fromLeft.size() != 0 && strength[i] >= strength[fromLeft.peek()]){
                ans[fromLeft.pop()*2+1] = i - 1;
            }
            fromLeft.push(i);
        }
        for(int i = strength.length-1; i >= 0; i--){
            while(fromRight.size() != 0 && strength[i] >= strength[fromRight.peek()]){
                ans[fromRight.pop()*2] = i + 1;
            }
            fromRight.push(i);
        }
        for(int i = 0; i < strength.length; i++){
            if(ans[2*i] < i - range[i]) ans[2*i] = i - range[i];
            if(ans[2*i+1] > i + range[i]) ans[2*i+1] = i + range[i];
        }
        if(strength.length == 1) return new int[] {0, 0};
        else return ans;
    }

    public static void main(String[] args) {
        Warriors sol = new Warriors();
        System.out.println(Arrays.toString(
            sol.warriors(new int[] {11, 13, 11, 11, 7, 15},
                        new int[] { 1,  8,  3, 1, 7,  2})));
        // Output: [0, 0, 0, 4, 2, 2, 3, 4, 4, 4, 3, 5]
    }
}
