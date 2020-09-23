import java.util.List;
import java.util.LinkedList;
import java.util.Arrays; 

public class Solution {
    public static List<int[]> threeSum(int[] nums) {
	List<int[]>ans = new LinkedList<>();
	//your solution process
	//if you want to add a solution [a,b,c] in the list, you can use:
	//ans.add(new int[]{a, b, c});
        return ans;
    }

    public static void main(String []args) {
        int[] test = {-1, 0, 1, 2, -2, -4};
        System.out.println(Solution.threeSum(test));
    }
}