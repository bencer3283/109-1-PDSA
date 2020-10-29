import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

class mountain{
    mountain(){
        valley1 = 0;
        valley2 = 0;
        peak = 0;
    }
    int valley1;
    int peak;
    int valley2;
}

public class Warriors {
    ArrayList<mountain> Mts = new ArrayList<mountain>();
    ArrayList<Integer> ans = new ArrayList<Integer>();
    
    public int findPeak(int[] strength, int pos){
        int i = 0;
        while(i+pos < strength.length){
            if(i+pos == strength.length - 1) return i+pos;
            else if(strength[i+pos] > strength[pos+i+1]) return i+pos;
            else if(strength[i+pos] <= strength[pos+i+1]) i++;
        }
        return pos;
    }
    public int findBottom(int[] strength, int pos){
        int i = 0;
        while(i+pos < strength.length){
            if(i+pos == strength.length - 1) return i+pos;
            else if(strength[i+pos] < strength[pos+i+1]) return i+pos;
            else if(strength[i+pos] >= strength[pos+i+1]) i++;
        }
        return pos;
    }
    public int[] warriors(int[] strength, int[] range) {
        //Given the attributes of each warriors and output the minimal and maximum 
        //index of warrior can be attacked by each warrior.
        int id = 0;
        while(id < strength.length - 1){
            int peak = findPeak(strength, id);
            int bottom = findBottom(strength, peak);
            mountain mt = new mountain();
            mt.peak = peak;
            mt.valley1 = id;
            mt.valley2 = bottom;
            Mts.add(mt);
            id = bottom;
            mt = null;
        }

        for(id = 0; id < strength.length; id++){
            int rangeLeft = id - range[id];
            if(rangeLeft < 0) rangeLeft = 0;
            int rangeRight = id + range[id];
            if(rangeRight > strength.length-1) rangeRight = strength.length-1;
            int boundLeft = id;
            int boundRight = id;
            int id_mt = 0;
            boolean atPeak = false;
            if(id < Mts.get(0).peak){
                boundRight = id;
                boundLeft = rangeLeft;
            }
            else if(id > Mts.get(Mts.size()-1).peak){
                boundLeft = id;
                boundRight = rangeRight;
            }
            else{
                for(id_mt = 0; id_mt < Mts.size(); id_mt++){
                    if(id == Mts.get(id_mt).peak){
                        atPeak = true;
                        //id_mt++;
                        break;
                    }
                    else if(id-Mts.get(id_mt).peak > 0 && Mts.get(id_mt+1).peak-id > 0) break;
                }
                //id_mt--;
                if(atPeak){
                    if(rangeLeft >= Mts.get(id_mt).valley1) boundLeft = rangeLeft;
                    else{
                        boundLeft = Mts.get(id_mt).valley1;
                        while(boundLeft >= 0 && strength[boundLeft] < strength[id]) boundLeft--;
                        boundLeft++;
                    }
                    if(rangeRight <= Mts.get(id_mt).valley2) boundRight = rangeRight;
                    else{
                        boundRight = Mts.get(id_mt).valley2;
                        while(boundRight < strength.length && strength[boundRight] < strength[id]) boundRight++;
                        boundRight--;
                    }
                    
                }
                else{
                    int slope = id - Mts.get(id_mt).valley2;
                    if(slope > 0){
                        boundRight = id;
                        boundLeft = id - slope;
                        while(boundLeft >= 0 && strength[boundLeft] < strength[id]) boundLeft--;
                        boundLeft++;
                    }
                    else if(slope < 0){
                        boundLeft = id;
                        boundRight = id - slope;
                        while(boundRight < strength.length &&strength[boundRight] < strength[id]) boundRight++;
                        boundRight--;
                    }
                    else{
                        boundLeft = id;
                        boundRight = id;
                    }
                }
            }
            if(boundLeft < rangeLeft) boundLeft = rangeLeft;
            if(boundRight > rangeRight) boundRight = rangeRight;
            ans.add(boundLeft);
            ans.add(boundRight);
        }
        int[] ANS = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            if(ans.get(i).intValue() < 0) ANS[i] = 0;
            else if(ans.get(i).intValue() > strength.length-1) ANS[i] = strength.length-1;
            else ANS[i] = ans.get(i).intValue();
        }
        return ANS;
    }

    public static void main(String[] args) {
        Warriors sol = new Warriors();
        System.out.println(Arrays.toString(
            sol.warriors(new int[] {11, 13, 11, 7, 15},
                        new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
    }
}
