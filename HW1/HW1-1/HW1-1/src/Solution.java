import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> Nums = new HashMap<Integer, Integer>();
        int targetMinusNum;
        int[] ans = {0, 0};
        for(int i = 0; i < nums.length; i++){
            targetMinusNum = target - nums[i];
            if(Nums.containsKey(targetMinusNum)){
                ans[0] = i;
                ans[1] = Nums.get(targetMinusNum);
            }
            else{
                Nums.put(nums[i], i);
            }
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String []args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.twoSum(new int[]{2,7,11,15}, 26)));
    }
}
