import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Map;
import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> Nums = new HashMap<Integer, Integer>();
        int targetMinusNum;
        int[] ans = {0, 0};
        for (int i = 0; i < nums.length; i++){
            targetMinusNum = target - nums[i];
            if (Nums.containsKey(targetMinusNum)){
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
    public static List<int[]> threeSum(Integer[] nums, int t) {
        List<int[]> ans = new LinkedList<>();
        // List<int[]>pairs = new LinkedList<>();
        Map<Integer, Integer> Nums = new HashMap<Integer, Integer>();
        for (int n = 0; n < nums.length; n++) {
            Nums.put(nums[n], nums[n]);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int[] pairs = {nums[i], nums[j], 0};
                int target = t - nums[i] - nums[j];
                if (target != nums[i] && target != nums[j]){
                    if (Nums.containsKey(target)) {
                        pairs[2] = target;
                        Arrays.sort(pairs);
                        int diffTimes = 0;
                        for (int k = 0; k < ans.size(); k++){
                            int[] cases = ans.get(k);
                            if(!Arrays.equals(cases, pairs)){
                                diffTimes++;
                            }
                        }
                        if (diffTimes == ans.size()){
                            ans.add(pairs);
                        }
                    }
                }
            }
        }
        return ans;
    }
    public static List<int[]> fourSum(int[] a, int target) {
        List<int[]> ans = new LinkedList<>();
        Map<Integer, Integer> positive = new HashMap<Integer, Integer>();
        Map<Integer, Integer> negative = new HashMap<Integer, Integer>();

        for (int n = 0; n < a.length; n++){
            if (a[n] >= 0){
                positive.put(a[n], a[n]);
            }
            else{
                negative.put(a[n], a[n]);
            }
        }

        //create array of positive numbers Integer[] posArray
        List<Integer> posList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : positive.entrySet()){
            posList.add(entry.getKey());
        }
        Integer[] posArray = posList.toArray(new Integer[0]);

        //create array of negative numbers Integer[] negArray
        List<Integer> negList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : negative.entrySet()){
            negList.add(entry.getKey());
        }
        Integer[] negArray = negList.toArray(new Integer[0]);

        //one negative & three positive numbers
        //for each negative number do a threesum
        for (Map.Entry<Integer, Integer> entry : negative.entrySet()){
            List<int[]> listof3 = threeSum(posArray, 0-entry.getKey());
            for (int i = 0; i < listof3.size(); i++){
                int[] pair1to3 = {0, 0, 0, 0};
                pair1to3[0] = listof3.get(i)[0];
                pair1to3[1] = listof3.get(i)[1];
                pair1to3[2] = listof3.get(i)[2];
                pair1to3[3] = entry.getKey();
                Arrays.sort(pair1to3);
                ans.add(pair1to3);
            }
        }

        //two negative & two positive numbers
        List<int[]> ans2to2 = new LinkedList<>();
        //form map of pairs, pair as key item as sum.
        Map<int[], Integer> posPairMap = new HashMap<int[], Integer>();
        for (int k = 0; k < posArray.length; k++){
            for (int l = k+1; l <posArray.length; l++){
                int[] pair = {posArray[k], posArray[l]};
                posPairMap.put(pair, posArray[k]+posArray[l]);
            }
        }
        Map<int[], Integer> negPairMap = new HashMap<int[], Integer>();
        for (int k = 0; k < negArray.length; k++){
            for (int l = k+1; l <negArray.length; l++){
                int[] pair = {negArray[k], negArray[l]};
                negPairMap.put(pair, negArray[k]+negArray[l]);
            }
        }
        for (Map.Entry<int[], Integer> entry2 : negPairMap.entrySet()){
            int target2 = 0-entry2.getValue();
            if (posPairMap.containsValue(target2)){
                for (int m = 0; m <= target2/2; m++){
                    int[] targetpair = {m, target2-m};
                    int[] inv_targetpair = {target2-m, m};
                    Boolean b = posPairMap.containsKey(targetpair);
                    if (posPairMap.containsKey(targetpair)){
                        int[] pair2to2 = {0, 0, 0, 0};
                        pair2to2[0]=entry2.getKey()[0];
                        pair2to2[1]=entry2.getKey()[1];
                        pair2to2[2]=targetpair[0];
                        pair2to2[3]=targetpair[1];
                        Arrays.sort(pair2to2);
                        int diffTimes2 = 0;
                        for (int k = 0; k < ans2to2.size(); k++){
                            int[] cases2 = ans2to2.get(k);
                            if(!Arrays.equals(cases2, pair2to2)){
                            diffTimes2++;
                            }
                        }
                        if (diffTimes2 == ans2to2.size()){
                            ans2to2.add(pair2to2);
                            ans.add(pair2to2);
                        }
                    }
                    else if (posPairMap.containsKey(inv_targetpair)){
                        int[] pair2to2 = {0, 0, 0, 0};
                        pair2to2[0]=entry2.getKey()[0];
                        pair2to2[1]=entry2.getKey()[1];
                        pair2to2[2]=inv_targetpair[0];
                        pair2to2[3]=inv_targetpair[1];
                        Arrays.sort(pair2to2);
                        int diffTimes2 = 0;
                        for (int k = 0; k < ans2to2.size(); k++){
                            int[] cases2 = ans2to2.get(k);
                            if(!Arrays.equals(cases2, pair2to2)){
                            diffTimes2++;
                            }
                        }
                        if (diffTimes2 == ans2to2.size()){
                            ans2to2.add(pair2to2);
                            ans.add(pair2to2);
                        }
                    }
                }
            }
        }

        //three negative & one positive numbers
        //one negative & three positive numbers
        //for each negative number do a threesum
        for (Map.Entry<Integer, Integer> entry : positive.entrySet()){
            List<int[]> listof3n = threeSum(negArray, 0-entry.getKey());
            for (int i = 0; i < listof3n.size(); i++){
                int[] pair1to3n = {0, 0, 0, 0};
                pair1to3n[0] = listof3n.get(i)[0];
                pair1to3n[1] = listof3n.get(i)[1];
                pair1to3n[2] = listof3n.get(i)[2];
                pair1to3n[3] = entry.getKey();
                Arrays.sort(pair1to3n);
                ans.add(pair1to3n);
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] test = { 1, 0, -1, -2, 2 };
        System.out.println(Solution.fourSum(test, 0).toString());
    }
}
