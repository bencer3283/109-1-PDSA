import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap; 

public class Solution {
    public static List<int[]> threeSum(int[] nums) {
        List<int[]> ans = new LinkedList<>();
        // List<int[]>pairs = new LinkedList<>();
        Map<Integer, Integer> Nums = new HashMap<Integer, Integer>();
        for (int n = 0; n < nums.length; n++) {
            Nums.put(nums[n], nums[n]);
        }
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                int[] pairs = {nums[i], nums[j], 0};
                int target = 0 - nums[i] - nums[j];
                if(target != nums[i] && target != nums[j]){
                    if (Nums.containsKey(target)) {
                        pairs[2] = target;
                        Arrays.sort(pairs);
                        int diffTimes = 0;
                        for(int k = 0; k < ans.size(); k++){
                            int[] cases = ans.get(k);
                            if(!Arrays.equals(cases, pairs)){
                                diffTimes++;
                            }
                        }
                        if(diffTimes == ans.size()){
                            ans.add(pairs);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test = { -1, 0, 1, 2, -2, -4 };
        System.out.println(Solution.threeSum(test).toString());
    }
}