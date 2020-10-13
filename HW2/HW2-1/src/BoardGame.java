
import java.util.HashMap;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class BoardGame {
    HashMap<Integer, Boolean> stoneType = new HashMap<Integer, Boolean>();
    HashMap<Integer, Integer> familySides = new HashMap<Integer, Integer>();
    WeightedQuickUnionUF stoneConnect;
    int size = 0;
    int board = 0;
    int height = 0;
    int width = 0;
        
    public BoardGame(int h, int w) {
        // create a board of size h*w
        stoneConnect = new WeightedQuickUnionUF(h*w);
        height = h;
        width = w;
    }
    
    public void putStone(int[] x, int[] y, char Type) {
        // put stones on the board according to the coordinate
        for(int i = 0; i < x.length; i++){
            int pos = y[i]+x[i]*100000;
            int index = y[i]*width+x[i];
            boolean type = false;
            if(Type == 'O') type = true;
            stoneType.put(pos, type);
            familySides.put(index, 4);

            int left = (x[i]-1)*100000+y[i];
            int indexLeft = (left%100000)*width + (left/100000);
            int right = (x[i]+1)*100000+y[i];
            int indexRight = (right%100000)*width + (right/100000);
            int top = x[i]*100000+y[i]+1;
            int indexTop = (top%100000)*width + (top/100000);
            int bottom = x[i]*100000+y[i]-1;
            int indexBottom = (bottom%100000)*width + (bottom/100000);
            int thisfamily = stoneConnect.find(index);

            if(stoneType.containsKey(left)){
                int compareToFamily = stoneConnect.find(indexLeft);
                if(stoneType.get(left)==stoneType.get(pos)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexLeft);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                }
            }
            if(stoneType.containsKey(right)){
                int compareToFamily = stoneConnect.find(indexRight);
                if(stoneType.get(left)==stoneType.get(pos)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexRight);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                }
            }
            if(stoneType.containsKey(top)){
                int compareToFamily = stoneConnect.find(indexTop);
                if(stoneType.get(left)==stoneType.get(pos)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexTop);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                }
            }
            if(stoneType.containsKey(bottom)){
                int compareToFamily = stoneConnect.find(indexBottom);
                if(stoneType.get(left)==stoneType.get(pos)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexBottom);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                }
            }
        }
    }

    public boolean surrounded(int x, int y) {
        // Answer if the stone and its connected stones are surrounded by another type of stones
        boolean sides = familySides.get(stoneConnect.find(y*width+x)) == 0;
        if(height < 3 || width < 3) return false;
        if(sides) return true;
        else return false;
    }

    public char getStoneType(int x, int y) {
        return 'O';
    }

    public static void main(String[] args) {
        BoardGame test = new BoardGame(3, 3);
        int[] array1 = {1};
        int[] array2 = {0, 1, 1};
        int[] array3 = {1, 0, 2};
        int[] array4 = {2};
        int[] array5 = {0};
        test.putStone(array1,array1, 'O');
        System.out.println(test.surrounded(1, 1));

        test.putStone(array2, array3, 'X');
        System.out.println(test.surrounded(1, 1));

        test.putStone(array4, array1, 'X');
        System.out.println(test.surrounded(1, 1));
        System.out.println(test.surrounded(2, 1));

        test.putStone(array4, array5, 'O');
        System.out.println(test.surrounded(2, 0));

    }
}

